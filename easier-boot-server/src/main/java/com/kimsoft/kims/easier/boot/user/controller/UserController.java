package com.kimsoft.kims.easier.boot.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimsoft.kims.easier.boot.api.UserApi;
import com.kimsoft.kims.easier.boot.common.LoginResponse;
import com.kimsoft.kims.easier.boot.common.QueryResult;
import com.kimsoft.kims.easier.boot.common.ResponseResult;
import com.kimsoft.kims.easier.boot.common.ReturnCodeEnum;
import com.kimsoft.kims.easier.boot.constant.CookieConstant;
import com.kimsoft.kims.easier.boot.constant.OrmConstant;
import com.kimsoft.kims.easier.boot.constant.RedisConstant;
import com.kimsoft.kims.easier.boot.constant.ServiceConstant;
import com.kimsoft.kims.easier.boot.converter.UserConverter;
import com.kimsoft.kims.easier.boot.dto.UserDto;
import com.kimsoft.kims.easier.boot.dto.UserQuery;
import com.kimsoft.kims.easier.boot.form.UserForm;
import com.kimsoft.kims.easier.boot.user.entity.User;
import com.kimsoft.kims.easier.boot.user.service.UserService;
import com.kimsoft.kims.easier.boot.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author kimi
 * @since 2020-04-17
 */
@RestController
@Slf4j
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResponseResult<UserDto> login(String account,
                                         String password,
                                         HttpServletResponse response) {
        if (log.isInfoEnabled()){
            log.info("start to login account={},password={}", account, password);
        }
        ResponseResult<UserDto> resp = null;
        try {
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.eq(OrmConstant.ACCOUNT,account);
            User user = userService.getOne(userWrapper);
            if (user == null) {
                resp =  createInvalidAccountResponse();
                return resp;
            }
            if (user.getPassword().equals(password)){
                // 生成token
                String token = genToken();
                if (log.isInfoEnabled()) {
                    log.info("end to login user={}", user);
                }
                resp = createLoginSuccessResponse(user,token);
                // 设置token至redis
                redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), String.valueOf(resp.getData()), CookieConstant.EXPIRE, TimeUnit.SECONDS);

                // 设置token至cookie
                CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

            }else {
                resp = createPasswordErrorResponse();
            }

            return resp;
        }catch (Exception e) {
            log.error("[系统登录]异常, " + e.getMessage());
            resp.setStatus(ReturnCodeEnum.FAIL.getValue());
            resp.setMessage("登录失败," + e.getMessage());
            return resp;
        }
    }

    @Override
    public ResponseResult<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            //1. 从cookie里查询
            Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
            if (cookie != null) {
                //2. 清除redis
                redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

                //3. 清除cookie
                CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
            }
            return new ResponseResult<>(ReturnCodeEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult<>(ReturnCodeEnum.FAIL);
        }
    }

    private String genToken() {
        return randomCode(ServiceConstant.random_token,32);
    }

    private String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder(size);

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int ioc = random.nextInt(s.length());
            result.append(s.charAt(ioc));
        }
        return result.toString();
    }

    private ResponseResult<UserDto> createPasswordErrorResponse() {
        ResponseResult<UserDto> resp = new ResponseResult<>();
        resp.setStatus(ReturnCodeEnum.ERROR_PASSWORD.getValue());
        resp.setMessage(ReturnCodeEnum.ERROR_PASSWORD.getDesc());
        return resp;
    }

    private ResponseResult<UserDto> createInvalidAccountResponse() {
        ResponseResult<UserDto> resp = new ResponseResult<>();
        resp.setStatus(ReturnCodeEnum.INVALID_ACCOUNT.getValue());
        resp.setMessage(ReturnCodeEnum.INVALID_ACCOUNT.getDesc());
        return resp;
    }
    private ResponseResult<UserDto> createLoginSuccessResponse(User user,String token) {
        LoginResponse<UserDto> resp = new LoginResponse<>(token);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        resp.setData(userDto);
        return resp;
    }
    @Override
    public ResponseResult<Map<String, String>> userSave(String orgCode, @Valid UserForm form) {
        ResponseResult<Map<String, String>> responseResult = new ResponseResult<>();
        User user = new User();
        Map<String, String> map = new HashMap<>();
        BeanUtils.copyProperties(form,user);
        user.setOrgCode(orgCode);
        if (form.getId() != null){
            user.setId(Long.valueOf(form.getId()));
        }
        userService.saveOrUpdate(user);
        map.put("id",user.getId().toString());
        responseResult.setData(map);
        return responseResult;
    }

    @Override
    public ResponseResult<UserDto> queryById(String orgCode, Long id) {
        ResponseResult<UserDto> responseResult = new ResponseResult<>();
        UserDto dto = new UserDto();
        User entity = userService.getById(id.toString());
        if (entity != null){
            BeanUtils.copyProperties(entity,dto);
        }
        responseResult.setData(dto);
        return responseResult;
    }

    @Override
    public ResponseResult<Void> delete(String orgCode, Long id) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            userService.removeById(id.toString());
        }catch (Exception e){
            responseResult.setStatus(ReturnCodeEnum.FAIL.getValue());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }

    @Override
    public ResponseResult<QueryResult<UserDto>> userPage(String orgCode, UserQuery query) {
        ResponseResult<QueryResult<UserDto>> responseResult = new ResponseResult<>();
        QueryResult<UserDto> queryResult = new QueryResult<>();
        Page<User> page = new Page<>(query.getPageIndex(), query.getPageSize());
        page.addOrder(OrderItem.desc("create_time"));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (query.getOrgCode() == null){
            query.setOrgCode(orgCode);
        }
        wrapper.eq("org_code",query.getOrgCode());
        if (query.getName() != null){
            wrapper.like("name",query.getName());
        }
        Page<User> userPage = userService.page(page,wrapper);
        List<UserDto> dtos = userPage.getRecords().stream().map(UserConverter::user2DTO).collect(Collectors.toList());
        queryResult.setPageIndex(userPage.getCurrent());
        queryResult.setPageSize(userPage.getSize());
        queryResult.setTotalCount(userPage.getTotal());
        queryResult.setRecords(dtos);
        responseResult.setData(queryResult);
        return responseResult;
    }

}

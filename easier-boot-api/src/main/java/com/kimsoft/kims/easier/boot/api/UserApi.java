package com.kimsoft.kims.easier.boot.api;

import com.kimsoft.kims.easier.boot.common.QueryResult;
import com.kimsoft.kims.easier.boot.common.ResponseResult;
import com.kimsoft.kims.easier.boot.dto.UserDto;
import com.kimsoft.kims.easier.boot.dto.UserQuery;
import com.kimsoft.kims.easier.boot.form.UserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author Kimi
 * @date 2020/4/17
 */
@Api(tags = "Admin - User Config Interface")
@RequestMapping("/middleware/admin/user")
public interface UserApi{

    @ApiOperation(value = "Login")
    @PostMapping(value = "/login")
    ResponseResult<UserDto> login(@RequestParam("account") String account, @RequestParam("password") String password, HttpServletResponse response);

    @ApiOperation(value = "Logout")
    @GetMapping(value = "/logout")
    ResponseResult<Void> logout(HttpServletRequest request,
                                HttpServletResponse response);


    @ApiOperation("Create / Update user")
    @PostMapping(value = "/save")
    ResponseResult<Map<String, String>> userSave(
            @RequestHeader("orgCode") String orgCode,
            @Valid @RequestBody UserForm form);

    @ApiOperation("Get user by id")
    @GetMapping(value = "/{id}")
    ResponseResult<UserDto> queryById(
            @RequestHeader("orgCode") @ApiParam(value = "登陆组织", required = true) String orgCode,
            @PathVariable("id") @ApiParam("主键Id") Long id);

    @ApiOperation("Remove")
    @DeleteMapping(value = "/delete")
    ResponseResult<Void> delete(
            @RequestHeader("orgCode") @ApiParam(value = "登陆组织", required = true) String orgCode,
            @RequestParam Long id);

    @ApiOperation("Get users by page")
    @PostMapping("/user/page")
    ResponseResult<QueryResult<UserDto>> userPage(@RequestHeader("orgCode") @ApiParam(value = "登陆组织", required = true) String orgCode,
                                                  @RequestBody @ApiParam("查询条件") UserQuery query);
}

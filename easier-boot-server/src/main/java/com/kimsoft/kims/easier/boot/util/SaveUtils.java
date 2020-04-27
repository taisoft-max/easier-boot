package com.kimsoft.kims.easier.boot.util;


import com.kimsoft.kims.easier.boot.common.ResponseResult;
import com.kimsoft.kims.easier.boot.common.ReturnCodeEnum;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kimi
 * @date 2020/4/11
 */
public class SaveUtils<E> {
    public static ResponseResult<Map<String, String>> postOperator(Long id ){
        Map<String, String> data = new HashMap<>();
        data.put("id", id.toString());
        return id == null ?  new ResponseResult<>(ReturnCodeEnum.FAIL) : new ResponseResult<>(data);
    }
    public static  <E> void preOperator(String orgCode, E param){
        Assert.isTrue(!StringUtils.isEmpty(orgCode), "orgCode不能为空");
        Assert.notNull(param, "body不能为空");
    }
}

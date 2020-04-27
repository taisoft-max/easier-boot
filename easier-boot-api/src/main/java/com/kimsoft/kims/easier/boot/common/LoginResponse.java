package com.kimsoft.kims.easier.boot.common;

import lombok.Data;

/**
 * @author Kimi
 * @date 2020/4/21
 */
@Data
public class LoginResponse<T> extends ResponseResult<T> {
    private String token;

    public LoginResponse(String token){
        this.token = token;
    }

}

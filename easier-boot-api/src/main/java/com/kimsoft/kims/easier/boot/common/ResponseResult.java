package com.kimsoft.kims.easier.boot.common;

/**
 * @author Kimi
 * @date 2020/3/28
 */
public class ResponseResult<T> {
    private String status = ReturnCodeEnum.SUCCESS.getValue();
    private String message = ReturnCodeEnum.SUCCESS.getDesc();
    private T data;


    public ResponseResult() {
    }

    public ResponseResult(T data) {
        this.data = data;
    }

    public ResponseResult(ReturnCodeEnum result) {
        if (result != null) {
            this.status = result.getValue();
            this.message = result.getDesc();
        }
    }

    public ResponseResult(String status, String message) {
        this.setStatus(status);
        this.message = message;
    }

    public ResponseResult(String status, String message, T data) {
        this.setStatus(status);
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

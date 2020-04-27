package com.kimsoft.kims.easier.boot.common;

/**
 * @author Kimi
 * @date 2020/3/28
 */
public enum ReturnCodeEnum {

    /**
     * SUCCESS
     */
    SUCCESS("0000", "成功"),

    INVALID_ACCOUNT("3002", "账号无效"),
    ERROR_PASSWORD("3003","密码错误"),
    NOT_LOGIN("3004","未登录"),
    BAD_REQUEST("0400", "请求参数错误"),
    AUTH_NOT_FOUND("0402", "授权信息不存在"),
    FAIL("0500", "处理失败，服务器异常")
    ;


    ReturnCodeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * The value.
     */
    private final String value;
    /**
     * The desc.
     */
    private final String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
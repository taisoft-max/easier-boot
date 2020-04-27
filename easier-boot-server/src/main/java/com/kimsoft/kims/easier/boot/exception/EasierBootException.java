package com.kimsoft.kims.easier.boot.exception;


import com.kimsoft.kims.easier.boot.common.ReturnCodeEnum;

/**
 * @author Kimi
 * @date 2020/3/28
 */
public class EasierBootException extends RuntimeException {
    private String code = ReturnCodeEnum.FAIL.getValue();
    private String desc = ReturnCodeEnum.FAIL.getDesc();

    public EasierBootException() {
    }

    public EasierBootException(String code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }

    public EasierBootException(String desc) {
        this(ReturnCodeEnum.FAIL.getValue(), desc);
    }

    public EasierBootException(ReturnCodeEnum codeEnum) {
        this(codeEnum.getValue(), codeEnum.getDesc());
    }

    public EasierBootException(ReturnCodeEnum codeEnum, String desc) {
        this(codeEnum.getValue(), codeEnum.getDesc() + (desc == null ? "" : desc));
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

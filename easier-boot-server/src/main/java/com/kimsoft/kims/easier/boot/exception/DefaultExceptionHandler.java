package com.kimsoft.kims.easier.boot.exception;
import com.kimsoft.kims.easier.boot.common.ResponseResult;
import com.kimsoft.kims.easier.boot.common.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Kimi
 * @date 2020/3/28
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(EasierBootException.class)
    public ResponseResult<Void> processShopInfException(EasierBootException ex) {
        log.error("EasierBootException:{}", ex.getDesc());
        ResponseResult<Void> result = new ResponseResult<>();
        result.setStatus(ex.getCode());
        result.setMessage(ex.getMessage());
        return result;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseResult<Void> processIllegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException:{}", ex.getMessage(), ex);
        ResponseResult<Void> result = new ResponseResult<>();
        result.setStatus(ReturnCodeEnum.BAD_REQUEST.getValue());
        result.setMessage(ex.getMessage());
        return result;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseResult<Void> processMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("IllegalArgumentException:{}", ex.getMessage(), ex);
        ResponseResult<Void> result = new ResponseResult<>();
        result.setStatus(ReturnCodeEnum.BAD_REQUEST.getValue());
        result.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> processIllegalArgumentException(Exception ex) {
        log.error("Exception:{}", ex.getMessage(), ex);
        ResponseResult<Void> result = new ResponseResult<>();
        result.setStatus(ReturnCodeEnum.FAIL.getValue());
        result.setMessage(ReturnCodeEnum.FAIL.getDesc());
        return result;
    }

}

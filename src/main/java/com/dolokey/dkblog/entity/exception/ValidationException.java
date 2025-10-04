package com.dolokey.dkblog.entity.exception;


import com.dolokey.dkblog.constant.LogConstant;

/**
 * 校验异常<br>
 * 继承于客户端异常，抛出给前端
 *
 * @author dolokey
 * @date 2025/09/23
 */
public class ValidationException extends ClientException {

    public ValidationException() {
        super(LogConstant.VALIDATION_EXCEPTION);
    }

    public ValidationException(String message) {
        super(message);
    }
}

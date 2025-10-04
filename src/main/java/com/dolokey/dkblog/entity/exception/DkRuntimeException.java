package com.dolokey.dkblog.entity.exception;

/**
 * 运行时异常
 *
 * @author dolokey
 * @date 2025/10/04
 */
public class DkRuntimeException extends RuntimeException {

    public DkRuntimeException() {
        super();
    }

    public DkRuntimeException(String message) {
        super(message);
    }
}

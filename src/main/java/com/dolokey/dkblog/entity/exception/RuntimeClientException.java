package com.dolokey.dkblog.entity.exception;

/**
 * 运行时客户端异常
 *
 * @author dolokey
 * @date 2025/10/04
 */
public class RuntimeClientException extends DkRuntimeException {

    public RuntimeClientException() {
        super();
    }

    public RuntimeClientException(String message) {
        super(message);
    }
}

/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.exception;


import lombok.NoArgsConstructor;

/**
 * 全局异常类
 *
 * @author chenjinyao
 * @date 2025/09/15
 */
public class DkException extends Exception{

    public DkException() {
        super();
    }

    public DkException(String message) {
        super(message);
    }
}

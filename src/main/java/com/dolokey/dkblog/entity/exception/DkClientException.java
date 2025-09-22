/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.exception;


import cn.hutool.core.text.CharSequenceUtil;
import com.dolokey.dkblog.constant.LogConstant;

/**
 * 客户端异常类
 *
 * @author chenjinyao
 * @date 2025/09/15
 */
public class DkClientException extends DkException {

    public DkClientException() {
        this(LogConstant.CLIENT_EXCEPTION);
    }

    public DkClientException(String message) {
        super(CharSequenceUtil.isBlank(message) ? LogConstant.CLIENT_EXCEPTION : message);
    }
}

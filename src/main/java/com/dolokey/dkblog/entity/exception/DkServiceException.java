/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.exception;


import cn.hutool.core.text.CharSequenceUtil;
import com.dolokey.dkblog.constant.LogConstant;

/**
 * 业务异常类
 *
 * @author chenjinyao
 * @date 2025/09/15
 */
public class DkServiceException extends DkException {

    public DkServiceException() {
        this(LogConstant.SERVICE_EXCEPTION);
    }

    public DkServiceException(String message) {
        super(CharSequenceUtil.isBlank(message) ? LogConstant.SERVICE_EXCEPTION : message);
    }
}

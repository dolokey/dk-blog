package com.dolokey.dkblog.entity.exception;


import cn.hutool.core.text.CharSequenceUtil;
import com.dolokey.dkblog.constant.LogConstant;

/**
 * 业务异常类
 *
 * @author dolokey
 * @date 2025/09/15
 */
public class ServiceException extends DkException {

    public ServiceException() {
        this(LogConstant.SERVICE_EXCEPTION);
    }

    public ServiceException(String message) {
        super(CharSequenceUtil.isBlank(message) ? LogConstant.SERVICE_EXCEPTION : message);
    }
}

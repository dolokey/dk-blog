package com.dolokey.dkblog.entity.exception;


import cn.hutool.core.text.CharSequenceUtil;
import com.dolokey.dkblog.constant.LogConstant;

/**
 * 客户端异常类
 *
 * @author dolokey
 * @date 2025/09/15
 */
public class ClientException extends DkException {

    public ClientException() {
        this(LogConstant.CLIENT_EXCEPTION);
    }

    public ClientException(String message) {
        super(CharSequenceUtil.isBlank(message) ? LogConstant.CLIENT_EXCEPTION : message);
    }
}

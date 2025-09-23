/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.config;

import com.dolokey.dkblog.constant.LogConstant;
import com.dolokey.dkblog.entity.api.R;
import com.dolokey.dkblog.entity.exception.ClientException;
import com.dolokey.dkblog.entity.exception.DkException;
import com.dolokey.dkblog.entity.exception.ServiceException;
import com.dolokey.dkblog.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * 全局异常处理类
 *
 * @author chenjinyao
 * @date 2025/09/15
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public R<Void> handleDkServiceException(ServiceException e) {
        log.error("业务异常捕获：{}", e.getMessage(), e);
        return R.fail(LogConstant.SERVICE_EXCEPTION);
    }

    @ExceptionHandler(ClientException.class)
    public R<Void> handleDkClientException(ClientException e) {
        log.info("客户端异常捕获：{}", e.getMessage());
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(DkException.class)
    public R<Void> handleDkException(DkException e) {
        log.error("项目异常捕获：{}", e.getMessage(), e);
        return R.fail(LogConstant.UNEXPECTED_EXCEPTION);
    }

    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e) {
        log.error("全局异常捕获：{}", e.getMessage(), e);
        return R.fail(LogConstant.UNEXPECTED_EXCEPTION);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> notSupportedException(HttpRequestMethodNotSupportedException e) {
        return R.fail(ResultCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public R<Void> noResourceFoundException(NoResourceFoundException e) {
        return R.fail(ResultCode.NOT_FOUND);
    }
}

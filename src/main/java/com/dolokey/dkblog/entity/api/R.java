/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.api;


import com.dolokey.dkblog.enums.ResultCode;
import lombok.Data;

/**
 * 接口返回对象
 *
 * @author chenjinyao
 * @date 2025/09/15
 */
@Data
public class R<T> {

    private R(ResultCode resultCode) {
        this(resultCode.getCode(), null, resultCode.getMessage());
    }

    private R(ResultCode resultCode, String message) {
        this(resultCode.getCode(), null, message);
    }

    private R(ResultCode resultCode, T data) {
        this(resultCode.getCode(), data, resultCode.getMessage());
    }

    private R(int code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.error = ResultCode.FAILURE.getCode() == code;
    }

    public static R<Void> success() {
        return new R<>(ResultCode.SUCCESS);
    }

    public static R<Void> success(String message) {
        return new R<>(ResultCode.SUCCESS, message);
    }

    public static <T> R<T> data(T data) {
        return new R<>(ResultCode.SUCCESS, data);
    }

    public static R<Void> fail() {
        return new R<>(ResultCode.FAILURE);
    }

    public static R<Void> fail(ResultCode resultCode) {
        return new R<>(resultCode);
    }

    public static R<Void> fail(String message) {
        return new R<>(ResultCode.FAILURE, message);
    }

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 是否异常
     */
    private boolean error;

    /**
     * 数据
     */
    private T data;
}

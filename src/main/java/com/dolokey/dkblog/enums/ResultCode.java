package com.dolokey.dkblog.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 接口返回枚举
 *
 * @author dolokey
 * @date 2025/09/15
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "请求成功"),
    /**
     * 失败
     */
    FAILURE(400, "请求失败"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),
    /**
     * 禁止访问
     */
    FORBIDDEN(403, "禁止访问"),
    /**
     * 资源未找到
     */
    NOT_FOUND(404, "资源未找到"),
    /**
     * 请求参数错误
     */
    METHOD_NOT_ALLOWED(405, "请求类型错误"),
    /**
     * 服务器异常
     */
    SERVER_ERROR(500, "服务器异常");


    /**
     * 响应值
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String message;
}

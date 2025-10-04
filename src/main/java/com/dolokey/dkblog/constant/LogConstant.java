package com.dolokey.dkblog.constant;


/**
 * 日志相关常量
 *
 * @author dolokey
 * @date 2025/09/16
 */
public class LogConstant {

    private LogConstant() {

    }

    /**
     * 未定义的异常
     */
    public static final String UNEXPECTED_EXCEPTION = "意料之外的异常";
    /**
     * 业务异常
     */
    public static final String SERVICE_EXCEPTION = "代码业务逻辑异常，请联系管理员";
    /**
     * 客户端调用错误
     */
    public static final String CLIENT_EXCEPTION = "客户端调用错误";
    /**
     * 校验异常
     */
    public static final String VALIDATION_EXCEPTION = "参数校验异常";
    /**
     * 系统配置异常
     */
    public static final String CONFIG_EXCEPTION = "系统配置异常";
    /**
     * 用户登录状态已过期，请重新登录
     */
    public static final String LOGIN_EXPIRED = "用户登录状态已过期，请重新登录";
    /**
     * 登录用户权限提示
     */
    public static final String LOGIN_USER_RIGHT = "需要登录后才能使用对应功能";
    /**
     * 管理员权限功能
     */
    public static final String ADMIN_RIGHT = "需要管理员权限才能使用对应功能";
}

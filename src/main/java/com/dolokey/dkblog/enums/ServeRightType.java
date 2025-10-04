package com.dolokey.dkblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务权限类型
 *
 * @author dolokey
 * @date 2025/10/04
 */
@Getter
@AllArgsConstructor
public enum ServeRightType {

    /**
     * 管理员
     */
    ADMIN(0, "管理员"),
    /**
     * 登录用户
     */
    USER(1, "登录用户"),
    /**
     * 游客
     */
    GUEST(2, "游客");

    /**
     * 状态值
     */
    private final Integer value;

    /**
     * 描述
     */
    private final String desc;
}

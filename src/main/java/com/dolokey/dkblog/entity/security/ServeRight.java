package com.dolokey.dkblog.entity.security;

import com.dolokey.dkblog.enums.ServeRightType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务权限注解
 *
 * @author dolokey
 * @date 2025/09/29
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServeRight {

    ServeRightType type();
}

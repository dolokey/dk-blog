package com.dolokey.dkblog.entity.security;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

/**
 * 日志注解
 *
 * @author dolokey
 * @date 2025/09/29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {

    Class<?> type();

    String desc() default StringPool.EMPTY;

    boolean manual() default false;

    String SAVE = "save";

    String UPDATE = "update";

    String DELETE = "delete";

    String LOGOUT = "logout";

    Set<String> SUPPORT_METHODS = Set.of(SAVE, UPDATE, DELETE, LOGOUT);
}

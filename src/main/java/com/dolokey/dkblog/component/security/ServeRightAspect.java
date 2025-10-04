package com.dolokey.dkblog.component.security;

import com.dolokey.dkblog.constant.LogConstant;
import com.dolokey.dkblog.entity.exception.ClientException;
import com.dolokey.dkblog.entity.security.ServeRight;
import com.dolokey.dkblog.enums.ServeRightType;
import com.dolokey.dkblog.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 服务权限切面
 *
 * @author dolokey
 * @date 2025/10/04
 */
@Aspect
@Component
public class ServeRightAspect {

    private static final Logger log = LogManager.getLogger(ServeRightAspect.class);

    /**
     * 切面点，切所有的控制类
     */
    @Pointcut("execution(* com.dolokey.dkblog.controller.*.*(..))")
    public void controllerAspect() {

    }

    @Around(value = "controllerAspect()", argNames = "joinPoint")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        // 用户是管理员 无视权限注解
        if (TokenUtil.isAdminUser()) {
            return joinPoint.proceed();
        }

        ServeRightType type = getServeRightType(joinPoint);

        // 登录用户权限校验
        if (type == ServeRightType.ADMIN) {
            if (!TokenUtil.isLogin(false)) {
                throw new ClientException(LogConstant.LOGIN_EXPIRED);
            }
            log.info("访问接口{}的{}接口需要管理员权限", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw new ClientException(LogConstant.ADMIN_RIGHT);
        } else if (type == ServeRightType.USER && !TokenUtil.isLogin(false)) {
            if (!TokenUtil.isLogin(false)) {
                throw new ClientException(LogConstant.LOGIN_EXPIRED);
            }
            log.info("访问接口{}的{}接口需要用户权限", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw new ClientException(LogConstant.LOGIN_USER_RIGHT);
        }
        return joinPoint.proceed();
    }

    /**
     * 获取权限类型
     *
     * @param joinPoint 切点
     * @return 权限类型
     */
    private static ServeRightType getServeRightType(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // 默认为游客权限
        ServeRightType type = ServeRightType.GUEST;

        // 获取权限注解
        Class<?> clazz = method.getDeclaringClass();
        if (method.isAnnotationPresent(ServeRight.class)) {
            type = method.getAnnotation(ServeRight.class).type();
        } else if (clazz.isAnnotationPresent(ServeRight.class)) {
            type = clazz.getAnnotation(ServeRight.class).type();
        }
        return type;
    }
}

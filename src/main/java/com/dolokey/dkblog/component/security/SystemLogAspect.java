package com.dolokey.dkblog.component.security;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.dolokey.dkblog.entity.api.R;
import com.dolokey.dkblog.entity.common.CoreEntity;
import com.dolokey.dkblog.entity.dto.LogDTO;
import com.dolokey.dkblog.entity.exception.ServiceException;
import com.dolokey.dkblog.entity.security.Logging;
import com.dolokey.dkblog.service.ILogService;
import com.dolokey.dkblog.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志切面
 *
 * @author dolokey
 * @date 2025/10/09
 */
@Slf4j
@Aspect
@Component
public class SystemLogAspect {

    /**
     * 请求参数
     */
    private final ThreadLocal<String> threadLocalParams = new ThreadLocal<>();
    /**
     * 请求时间
     */
    private final ThreadLocal<Long> threadLocalTime = new ThreadLocal<>();

    /**
     * 获取记录开始时间
     *
     * @return 记录开始时间
     */
    public static Date getLogStartTime() {
        return new Date(SpringUtil.getBean(SystemLogAspect.class).threadLocalTime.get());
    }

    /**
     * 获取请求参数
     *
     * @return 请求参数
     */
    public static String getLogParams() {
        return SpringUtil.getBean(SystemLogAspect.class).threadLocalParams.get();
    }

    /**
     * 日志切面
     */
    @Pointcut("@annotation(com.dolokey.dkblog.entity.security.Logging)")
    public void loggingAspect() {
    }

    @Around("loggingAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String className = args[i].getClass().getSimpleName();
            String key = className + "_" + i;
            requestParams.put(key, args[i]);
        }
        threadLocalParams.set(JSONUtil.toJsonStr(requestParams));
        threadLocalTime.set(System.currentTimeMillis());

        try {
            return joinPoint.proceed();
        } finally {
            threadLocalParams.remove();
            threadLocalTime.remove();
        }
    }

    @AfterReturning(pointcut = "loggingAspect()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) throws ServiceException {
        HttpServletRequest request = TokenUtil.getRequest();
        String token = TokenUtil.getToken(request);
        // 为模拟请求时为清空请求 放过记录日志
        if (!(request instanceof MockHttpServletRequest) && CharSequenceUtil.isBlank(token)) {
            return;
        }
        // 构建日志
        LogDTO logDTO = buildLog(joinPoint);
        // 获取方法名称
        if (result instanceof R<?> r && (r.getData() instanceof String || r.getData() instanceof Long)) {
            String object = (r.getData()).toString();
            String methodName = joinPoint.getSignature().getName();
            // 保存更新删除时取返回值作为对象编号
            if (Logging.SUPPORT_METHODS.contains(methodName)) {
                String[] idArr = CoreEntity.convert(object);
                // 如果没有值直接保存
                if (idArr.length == 0) {
                    SpringUtil.getBean(ILogService.class).save(logDTO);
                } else {
                    // 有值复制后保存多个对象
                    for (String id : CoreEntity.convert(object)) {
                        LogDTO log = BeanUtil.toBean(logDTO, LogDTO.class);
                        log.setObject(Long.valueOf(id));
                        SpringUtil.getBean(ILogService.class).save(log);
                    }
                }
            }
        } else {
            SpringUtil.getBean(ILogService.class).save(logDTO);
        }
    }

    @AfterThrowing(pointcut = "loggingAspect()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = TokenUtil.getRequest();
        String token = TokenUtil.getToken(request);
        // 为模拟请求时为清空请求 放过记录日志
        if (!(request instanceof MockHttpServletRequest) && CharSequenceUtil.isBlank(token)) {
            return;
        }
        // 构建日志
        LogDTO logDTO = buildLog(joinPoint);
        // 获取异常信息
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        logDTO.setErrorMsg(sw.toString());
        SpringUtil.getBean(ILogService.class).save(logDTO);
    }

    /**
     * 构建日志
     *
     * @param joinPoint 切点
     * @return 日志
     */
    public static LogDTO buildLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logging logAnnotation = method.getAnnotation(Logging.class);
        // 获取注解参数和耗时
        Class<?> type = logAnnotation.type();
        LogDTO logDTO = new LogDTO();
        logDTO.setType(type.getSimpleName());
        logDTO.setOperateDesc(logAnnotation.desc());
        logDTO.setParams(getLogParams());
        return logDTO;
    }
}

package com.dolokey.dkblog.entity.exception;


import com.dolokey.dkblog.constant.LogConstant;

/**
 * 配置异常<br>
 * 由于该项目基于特定需求实现，所以在部分场景下需要安装特殊要求进行配置，来保障系统逻辑的自洽<br>
 *
 * @author dolokey
 * @date 2025/10/03
 */
public class ConfigException extends ServiceException {

    /**
     * 配置异常唯一构造器<br>
     * 当需要创建一个配置异常时必须描述配置异常的情况<br>
     *
     * @param message 描述配置异常
     */
    public ConfigException(String message) {
        super(LogConstant.CONFIG_EXCEPTION + "：" + message);
    }
}

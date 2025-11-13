package com.dolokey.dkblog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日志操作结果枚举
 *
 * @author dolokey
 * @date 2025/10/04
 */
@Getter
@AllArgsConstructor
public enum LogOperateResult {

    /**
     * 失败
     */
    FAIL(0, "失败"),
    /**
     * 成功
     */
    SUCCESS(1, "成功");


    /**
     * 状态值
     */
    @EnumValue
    private final Integer value;

    /**
     * 描述
     */
    private final String desc;

}

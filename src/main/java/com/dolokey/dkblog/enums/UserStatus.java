package com.dolokey.dkblog.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dolokey
 * @date 2025/9/15
 */
@Getter
@AllArgsConstructor
public enum UserStatus {

    /**
     * 已删除
     */
    DELETED(-1, "已删除"),
    /**
     * 正常
     */
    NORMAL(0, "正常"),
    /**
     * 禁用
     */
    DISABLED(1, "禁用");

    /**
     * 状态值
     */
    @EnumValue
    private final Integer value;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 根据状态值获取枚举
     *
     * @param value 状态值
     * @return 枚举
     */
    public static UserStatus getEnum(Integer value) {
        for (UserStatus status : UserStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }
}

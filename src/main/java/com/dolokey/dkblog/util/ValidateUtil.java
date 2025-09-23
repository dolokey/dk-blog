/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.util;


import cn.hutool.core.text.CharSequenceUtil;
import com.dolokey.dkblog.constant.StringPool;
import com.dolokey.dkblog.entity.exception.ServiceException;
import com.dolokey.dkblog.entity.exception.ValidationException;

/**
 * 校验工具
 *
 * @author chenjinyao
 * @date 2025/09/23
 */
public class ValidateUtil {

    private ValidateUtil() {

    }

    /**
     * 校验字符串<br>
     * 通常用于数据库校验<br>
     * max时为0时会忽略长度校验，min在填写时不允许大于max<br>
     *
     * @param value     值
     * @param fieldName 字段名称
     * @param min       最小长度
     * @param max       最大长度
     * @param isRequire 是否必填
     * @return 校验后值
     * @throws ValidationException 校验异常
     * @throws ServiceException    调用异常
     */
    public static String validate(String value, String fieldName, int min, int max, boolean isRequire) throws ValidationException, ServiceException {
        if (min > max) {
            throw new ServiceException("字段长度限制异常");
        }
        if (CharSequenceUtil.isBlank(value)) {
            if (isRequire) {
                throw new ValidationException(fieldName + "不能为空！");
            } else {
                // 数据库字段非必要不存储空值
                return StringPool.EMPTY;
            }
        }
        value = value.trim();
        // 如果长度为空，则不进行校验
        if (max <= 0) {
            return value;
        } else if (min <= 0 && value.length() > max) {
            throw new ValidationException(fieldName + "长度不能超过" + max + "！");
        } else if (value.length() < min || value.length() > max) {
            throw new ValidationException(fieldName + "长度必须在" + min + "到" + max + "之间！");
        }
        return value;
    }
}

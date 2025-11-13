package com.dolokey.dkblog.entity.common;


import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dolokey.dkblog.constant.CommonConstant;
import com.dolokey.dkblog.constant.StringConstant;
import com.dolokey.dkblog.entity.exception.ServiceException;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 核心对象<br>
 * 所有对象都需要创建时间和创建用户<br>
 *
 * @author dolokey
 * @date 2025/09/29
 */
@Data
public class CoreEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "cr_time", fill = FieldFill.INSERT)
    protected Date crTime;

    /**
     * 创建用户
     */
    @TableField(value = "cr_user", fill = FieldFill.INSERT)
    protected String crUser;

    /**
     * 转换编号
     */
    public static String[] convert(String ids) throws ServiceException {
        if (CharSequenceUtil.isBlank(ids)) {
            return CommonConstant.EMPTY_STRING_ARRAY;
        }
        String[] idArr = ids.split(StringConstant.COMMA);
        for (String id : idArr) {
            if (!CharSequenceUtil.isNumeric(id)) {
                throw new ServiceException("编号[" + id + "]不是数字");
            }
        }
        return idArr;
    }

    /**
     * 转换编号
     */
    public static List<String> converts(String ids) throws ServiceException {
        return List.of(convert(ids));
    }
}

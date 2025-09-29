/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.common;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * 基础对象<br>
 * 在核心对象的基础，对于需要记录更新情况的对象
 * @author chenjinyao
 * @date 2025/09/20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends CoreEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 修改时间
     */
    @TableField(value = "up_time", fill = FieldFill.INSERT_UPDATE)
    protected Date upTime;

    /**
     * 修改用户
     */
    @TableField(value = "up_user", fill = FieldFill.INSERT_UPDATE)
    protected String upUser;
}

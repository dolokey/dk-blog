/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.common;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.*;
import java.util.Date;

/**
 * 基础对象
 *
 * @author chenjinyao
 * @date 2025/09/20
 */
@Data
public class TEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
     * 修改时间
     */
    @TableField(value = "up_time", fill = FieldFill.INSERT_UPDATE)
    protected Date upTime;

    /**
     * 修改用户
     */
    @TableField(value = "up_user", fill = FieldFill.INSERT_UPDATE)
    protected String upUser;

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }
}

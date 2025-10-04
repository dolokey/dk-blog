package com.dolokey.dkblog.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dolokey.dkblog.entity.common.BaseEntity;
import com.dolokey.dkblog.enums.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户对象
 *
 * @author dolokey
 * @date 2025/09/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dk_user")
public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 头像路径
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 用户状态
     */
    @TableField(value = "status")
    private UserStatus status;

    /**
     * 密码登录错误次数
     */
    @TableField(value = "error_num")
    private Integer errorNum;
}

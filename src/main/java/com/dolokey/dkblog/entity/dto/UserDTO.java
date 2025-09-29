/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.dto;


import lombok.Data;

/**
 * 用户数据传输对象
 *
 * @author chenjinyao
 * @date 2025/09/23
 */
@Data
public class UserDTO {

    /**
     * 用户编号
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     *
     */

}

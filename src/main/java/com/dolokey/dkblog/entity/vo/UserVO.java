/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.vo;


import lombok.Data;

/**
 * 用户视图对象
 *
 * @author chenjinyao
 * @date 2025/09/23
 */
@Data
public class UserVO {

    /**
     * 用户编号
     */
    private String id;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;
}

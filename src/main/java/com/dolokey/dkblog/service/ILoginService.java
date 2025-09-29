/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.service;


import com.dolokey.dkblog.entity.dto.UserDTO;
import com.dolokey.dkblog.entity.exception.ClientException;

/**
 * 登录接口
 *
 * @author chenjinyao
 * @date 2025/09/29
 */
public interface ILoginService {
    void login(UserDTO userDTO) throws ClientException;
}

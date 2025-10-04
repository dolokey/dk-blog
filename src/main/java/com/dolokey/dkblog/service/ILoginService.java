package com.dolokey.dkblog.service;


import com.dolokey.dkblog.entity.dto.UserDTO;
import com.dolokey.dkblog.entity.exception.ClientException;

/**
 * 登录接口
 *
 * @author dolokey
 * @date 2025/09/29
 */
public interface ILoginService {
    String login(UserDTO userDTO) throws ClientException;
}

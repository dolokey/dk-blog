/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.service.impl;


import com.dolokey.dkblog.entity.dto.UserDTO;
import com.dolokey.dkblog.entity.exception.ClientException;
import com.dolokey.dkblog.model.User;
import com.dolokey.dkblog.service.ILoginService;
import com.dolokey.dkblog.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 登录接口实现
 *
 * @author chenjinyao
 * @date 2025/09/29
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private IUserService userService;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Override
    public void login(UserDTO userDTO) throws ClientException {
        User user = userService.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new ClientException("用户名或密码不正确");
        }
        if (!encoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new ClientException("用户名或密码不正确");
        }
    }
}

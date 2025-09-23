/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.entity.dto.UserDTO;
import com.dolokey.dkblog.entity.exception.ServiceException;
import com.dolokey.dkblog.entity.exception.ValidationException;
import com.dolokey.dkblog.mapper.UserMapper;
import com.dolokey.dkblog.model.User;
import com.dolokey.dkblog.service.IUserService;
import com.dolokey.dkblog.util.ValidateUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户接口实现类
 *
 * @author chenjinyao
 * @date 2025/09/22
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> list(UserDTO searchBean, Page<User> page) {
        return userMapper.selectList(page, null);
    }

    @Override
    public int save(UserDTO userDTO) throws ValidationException, ServiceException {
        saveCheck(userDTO);
        User user = BeanUtil.toBean(userDTO, User.class);
        return userMapper.insert(user);
    }

    protected void saveCheck(UserDTO userDTO) throws ValidationException, ServiceException {
        userDTO.setUsername(ValidateUtil.validate(userDTO.getUsername(), "用户名", 0, 64, true));
        userDTO.setNickname(ValidateUtil.validate(userDTO.getNickname(), "用户昵称", 0, 64, true));
        userDTO.setPassword(ValidateUtil.validate(userDTO.getPassword(), "用户密码", 6, 20, true));
        userDTO.setAvatar(ValidateUtil.validate(userDTO.getAvatar(), "用户头像", 0, 128, false));
        if (userMapper.exists(new LambdaQueryWrapper<User>().eq(User::getUsername, userDTO.getUsername()).ne(User::getId, userDTO.getId()))) {
            throw new ValidationException("用户名已存在");
        }
    }
}

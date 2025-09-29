/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Resource
    private BCryptPasswordEncoder encoder;

    @Override
    public List<User> list(UserDTO searchBean, Page<User> page) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, searchBean.getUsername());
        return userMapper.selectList(page, queryWrapper);
    }

    @Override
    public Long save(UserDTO userDTO) throws ValidationException, ServiceException {
        saveCheck(userDTO);
        User user = BeanUtil.toBean(userDTO, User.class);
        // 加密密码
        user.setPassword(encoder.encode(user.getPassword()));
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public void update(UserDTO userDTO) throws ValidationException, ServiceException {
        saveCheck(userDTO);
        User user = userMapper.findByIdThrowEx(userDTO.getId());
        BeanUtil.copyProperties(userDTO, user);
        // 加密密码
        user.setPassword(encoder.encode(user.getPassword()));
        userMapper.updateById(user);
    }

    @Override
    public User findByUsername(String username) {
        if (CharSequenceUtil.isBlank(username)) {
            return null;
        }
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    protected void saveCheck(UserDTO userDTO) throws ValidationException, ServiceException {
        userDTO.setUsername(ValidateUtil.validate(userDTO.getUsername(), "用户名", 0, 64, true));
        userDTO.setNickname(ValidateUtil.validate(userDTO.getNickname(), "用户昵称", 0, 64, true));
        userDTO.setPassword(ValidateUtil.validate(userDTO.getPassword(), "用户密码", 6, 20, true));
        userDTO.setAvatar(ValidateUtil.validate(userDTO.getAvatar(), "用户头像", 0, 128, false));
        if (userMapper.exists(new LambdaQueryWrapper<User>().eq(User::getUsername, userDTO.getUsername()))) {
            throw new ValidationException("用户名已存在");
        }
    }
}

/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.mapper.UserMapper;
import com.dolokey.dkblog.model.User;
import com.dolokey.dkblog.service.IUserService;
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
    public Page<User> list(Page<User> page) {
        userMapper.selectList(page, null);
        return page;
    }
}

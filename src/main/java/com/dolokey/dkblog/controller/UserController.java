/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.entity.api.MR;
import com.dolokey.dkblog.model.User;
import com.dolokey.dkblog.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 *
 * @author chenjinyao
 * @date 2025/09/22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/list")
    public MR<User> list(Page<User> page) {
        Page<User> list = userService.list(page);
        return MR.data(list);
    }
}

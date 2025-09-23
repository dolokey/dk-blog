/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.controller;


import com.dolokey.dkblog.entity.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制层
 *
 * @author chenjinyao
 * @date 2025/09/16
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/login")
    public R<Void> login() {
        return R.success();
    }
}

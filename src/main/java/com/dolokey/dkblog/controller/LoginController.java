package com.dolokey.dkblog.controller;


import com.dolokey.dkblog.entity.api.R;
import com.dolokey.dkblog.entity.dto.UserDTO;
import com.dolokey.dkblog.entity.exception.ClientException;
import com.dolokey.dkblog.entity.security.Logging;
import com.dolokey.dkblog.entity.security.LoginUser;
import com.dolokey.dkblog.service.ILoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制层
 *
 * @author dolokey
 * @date 2025/09/16
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private ILoginService loginService;

    @PostMapping("/login")
    @Logging(type = LoginUser.class, desc = "用户登录", manual = true)
    public R<String> login(UserDTO userDTO) throws ClientException {
        String token = loginService.login(userDTO);
        return R.data(token);
    }


    @PostMapping("/logout")
    @Logging(type = LoginUser.class, desc = "用户登出")
    public R<Long> logout() throws ClientException {
        Long userId = loginService.logout();
        return R.data(userId);
    }
}

package com.dolokey.dkblog.service.impl;


import com.dolokey.dkblog.component.security.SystemLogAspect;
import com.dolokey.dkblog.constant.LogConstant;
import com.dolokey.dkblog.entity.dto.LogDTO;
import com.dolokey.dkblog.entity.dto.UserDTO;
import com.dolokey.dkblog.entity.exception.ClientException;
import com.dolokey.dkblog.entity.exception.DkRuntimeException;
import com.dolokey.dkblog.entity.security.LoginUser;
import com.dolokey.dkblog.model.User;
import com.dolokey.dkblog.service.ILogService;
import com.dolokey.dkblog.service.ILoginService;
import com.dolokey.dkblog.service.IUserService;
import com.dolokey.dkblog.util.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 登录接口实现
 *
 * @author dolokey
 * @date 2025/09/29
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private IUserService userService;

    @Resource
    private ILogService logService;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Override
    public String login(UserDTO userDTO) throws ClientException {
        User user = userService.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new ClientException("用户名或密码不正确");
        }
        if (!encoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new ClientException("用户名或密码不正确");
        }
        // 保存用户日志
        LogDTO logDTO = new LogDTO();
        logDTO.setObject(user.getId());
        logDTO.setType(LoginUser.class.getSimpleName());
        logDTO.setParams(SystemLogAspect.getLogParams());
        logDTO.setOperateDesc("用户" + user.getNickname() + "登录");
        logService.save(logDTO);
        return TokenUtil.setLoginUser(user);
    }

    @Override
    public Long logout() throws ClientException {
        try {
            LoginUser loginUser = TokenUtil.getLoginUser();
            // 用户登出
            TokenUtil.userLogout();
            return loginUser.getUserId();
        } catch (DkRuntimeException e) {
            if (LogConstant.LOGIN_EXPIRED.equals(e.getMessage())) {
                throw new ClientException("用户未登录");
            }
            throw e;
        }
    }

}

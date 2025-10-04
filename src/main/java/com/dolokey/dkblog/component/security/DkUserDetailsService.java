package com.dolokey.dkblog.component.security;


import com.dolokey.dkblog.entity.security.LoginUser;
import com.dolokey.dkblog.model.User;
import com.dolokey.dkblog.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户认证服务
 *
 * @author dolokey
 * @date 2025/09/20
 */
public class DkUserDetailsService implements UserDetailsService {

    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new LoginUser(user);
    }
}

package com.dolokey.dkblog.entity.security;


import com.dolokey.dkblog.enums.UserStatus;
import com.dolokey.dkblog.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 权限用户详情
 *
 * @author dolokey
 * @date 2025/09/20
 */
@Data
public class LoginUser implements UserDetails {

    public LoginUser(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.user = user;
    }

    /**
     * 用户
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户实例
     */
    private User user;

    /**
     * 到期时间
     */
    private Date expireTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserStatus.NORMAL.equals(user.getStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserStatus.DISABLED.equals(user.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

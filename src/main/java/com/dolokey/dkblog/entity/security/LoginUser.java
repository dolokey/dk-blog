/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.security;


import com.dolokey.dkblog.enums.UserStatus;
import com.dolokey.dkblog.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 权限用户详情
 *
 * @author chenjinyao
 * @date 2025/09/20
 */
@Data
public class LoginUser implements UserDetails {

    /**
     * 用户
     */
    private Integer userId;

    /**
     * 用户Token
     */
    private String token;

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
        return UserStatus.NORMAL.getValue().equals(user.getStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserStatus.DISABLED.getValue().equals(user.getStatus());
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

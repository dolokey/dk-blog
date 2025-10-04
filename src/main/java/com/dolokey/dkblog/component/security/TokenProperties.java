package com.dolokey.dkblog.component.security;


import cn.hutool.core.text.CharSequenceUtil;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 登录校验属性配置类
 *
 * @author dolokey
 * @date 2025/10/03
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "dk.security.token")
public class TokenProperties {

    @PostConstruct
    public void init() {
        if (!CharSequenceUtil.isNumeric(expireTime)) {
            expireTime = "2400";
        }
    }

    /**
     * 请求头名称
     */
    private String header = "Authorization";

    /**
     * 密钥
     */
    private String secret = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";

    /**
     * 过期时间
     */
    private String expireTime = "2400";

    /**
     * 管理员用户名
     */
    private String admin = "dolphin";

    /**
     * 用户昵称 在初始化时因安全需求必须配置
     */
    private String nickname;

    /**
     * 默认密码
     */
    private String defaultPassword = "123456";
}

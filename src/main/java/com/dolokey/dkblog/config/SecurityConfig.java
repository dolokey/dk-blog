package com.dolokey.dkblog.config;


import com.dolokey.dkblog.component.security.DkAuthenticationProvider;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 项目安全配置
 *
 * @author dolokey
 * @date 2025/09/16
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private DkAuthenticationProvider dkAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 禁用csrf 通过jwt进行校验
        http.csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(dkAuthenticationProvider);
        return http.build();
    }

    /**
     * 密码编码器<br>
     * 保存需要加密的密码时使用
     *
     * @return 密码编码器
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

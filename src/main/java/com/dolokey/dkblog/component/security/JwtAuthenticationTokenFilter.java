package com.dolokey.dkblog.component.security;

import cn.hutool.json.JSONUtil;
import com.dolokey.dkblog.entity.api.R;
import com.dolokey.dkblog.entity.exception.DkRuntimeException;
import com.dolokey.dkblog.entity.security.LoginUser;
import com.dolokey.dkblog.util.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * jwt过滤器
 *
 * @author dolokey
 * @date 2025/10/04
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            LoginUser loginUser = TokenUtil.getLoginUser();
            if (loginUser != null) {
                TokenUtil.refreshToken(null);
            }
        } catch (DkRuntimeException e) {
            R<Void> result = R.fail(e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(JSONUtil.toJsonStr(result));
            log.error("登录权限校验异常：{}", e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }
}

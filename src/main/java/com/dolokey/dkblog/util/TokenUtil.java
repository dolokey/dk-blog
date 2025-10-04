package com.dolokey.dkblog.util;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.dolokey.dkblog.component.security.TokenProperties;
import com.dolokey.dkblog.constant.CommonConstant;
import com.dolokey.dkblog.entity.dto.UserDTO;
import com.dolokey.dkblog.entity.exception.ConfigException;
import com.dolokey.dkblog.entity.exception.DkException;
import com.dolokey.dkblog.entity.exception.DkRuntimeException;
import com.dolokey.dkblog.entity.exception.ServiceException;
import com.dolokey.dkblog.entity.security.LoginUser;
import com.dolokey.dkblog.mapper.UserMapper;
import com.dolokey.dkblog.model.User;
import com.dolokey.dkblog.service.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Token验证工具类<br>
 *
 * @author dolokey
 * @date 2025/09/30
 */
@Slf4j
public class TokenUtil {

    /**
     * 字段常量
     */
    private static final String USER_ID = "userId";
    private static final String USERNAME = "username";
    private static final String LOGIN_TIME = "loginTime";
    private static final String SALT = "salt";
    public static final String UNKNOWN = "unknown";

    private TokenUtil() {

    }

    /**
     * 登录用户信息缓存池<br>
     * 基于redis存储用户实现单点登录的基础上简化而舍弃单点登录不需要redis的本地实现<br>
     */
    private static final Map<Long, LoginUser> LOGIN_USER_INFO = new ConcurrentHashMap<>();

    /**
     * 管理员用户名
     */
    @Getter
    private static String adminUser;

    /**
     * 获取配置属性
     */
    private static TokenProperties properties() {
        return SpringUtil.getBean(TokenProperties.class);
    }

    /**
     * 创建管理员用户<br>
     * 在不存在用户时创建管理员用户
     */
    public static void initAdminUser() throws ServiceException {
        TokenProperties properties = properties();

        IUserService userService = SpringUtil.getBean(IUserService.class);
        User adminUserInstance = userService.findByUsername(properties.getAdmin());
        // 如果管理员用户不存在
        if (adminUserInstance == null) {
            Long count = SpringUtil.getBean(UserMapper.class).selectCount(null);
            if (count.compareTo(0L) > 0) {
                throw new ConfigException("需指定存在的用户作为管理员用户");
            }
            if (CharSequenceUtil.isBlank(properties().getNickname())) {
                throw new ConfigException("请配置管理员昵称");
            }
            try {
                UserDTO userDTO = new UserDTO();
                userDTO.setUsername(properties.getAdmin());
                userDTO.setNickname(properties.getNickname());
                userDTO.setPassword(properties.getDefaultPassword());
                userService.save(userDTO);
            } catch (DkException e) {
                throw new ServiceException("初始化程序异常，请管理员进行排查：" + e.getMessage());
            }
        }
        TokenUtil.adminUser = properties.getAdmin();
    }


    public static boolean isAdminUser() {
        String loginUsername = getLoginUsername();
        return adminUser.equals(loginUsername);
    }

    /**
     * 获取当前线程请求对象
     *
     * @return 请求
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 根据请求获取Token
     *
     * @param request 请求
     * @return token
     */
    public static String getToken(HttpServletRequest request) {
        if (request == null) {
            throw new DkRuntimeException("空请求");
        }
        String token = request.getHeader(properties().getHeader());
        if (CharSequenceUtil.isNotBlank(token) && token.startsWith(CommonConstant.TOKEN_PREFIX)) {
            token = token.replace(CommonConstant.TOKEN_PREFIX, StringPool.EMPTY);
        }
        return token;
    }

    /**
     * 获取SecretKey
     *
     * @return SecretKey
     */
    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(properties().getSecret().getBytes());
    }

    /**
     * 生成Token
     *
     * @param loginUser 登录用户
     * @return token
     */
    public static String generateToken(LoginUser loginUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, loginUser.getUserId());
        claims.put(USERNAME, loginUser.getUsername());
        claims.put(LOGIN_TIME, DateUtil.now());
        claims.put(SALT, IdUtil.simpleUUID());

        SecretKey secretKey = getSecretKey();
        return Jwts.builder()
                .claims(claims)
                .signWith(secretKey)
                .compact();
    }

    /**
     * 解析Token
     *
     * @param token token
     * @return claims
     */
    public static Claims parseToken(String token) {
        try {
            SecretKey secretKey = getSecretKey();
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new DkRuntimeException("非法的登录凭证");
        }
    }

    /**
     * 获取登录用户
     *
     * @return 用户
     */
    public static LoginUser getLoginUser() {
        HttpServletRequest request = getRequest();
        String token = getToken(request);
        return getLoginUserByToken(token);
    }

    /**
     * 根据Token获取登录用户
     *
     * @param token token
     * @return 用户
     */
    public static LoginUser getLoginUserByToken(String token) {
        if (CharSequenceUtil.isBlank(token)) {
            return null;
        }
        Claims claims = parseToken(token);
        Long userId = claims.get(USER_ID, Long.class);
        return LOGIN_USER_INFO.get(userId);
    }

    /**
     * 获取登录用户名
     *
     * @return 用户名
     */
    public static String getLoginUsername() {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return UNKNOWN;
        }
        return loginUser.getUsername();
    }

    /**
     * 设置登录用户
     *
     * @param user 用户
     * @return token
     */
    public static String setLoginUser(User user) {
        LoginUser loginUser = new LoginUser(user);
        LOGIN_USER_INFO.put(user.getId(), loginUser);
        String token = generateToken(loginUser);
        refreshToken(token);
        return token;
    }

    /**
     * 刷新Token
     */
    public static void refreshToken(String token) {
        LoginUser loginUser = CharSequenceUtil.isBlank(token) ? getLoginUser() : getLoginUserByToken(token);
        if (loginUser != null) {
            DateTime date = DateUtil.date();
            Date expireTime = date.offsetNew(DateField.MINUTE, Integer.parseInt(properties().getExpireTime()));
            loginUser.setExpireTime(expireTime);
        }
    }

    /**
     * 登出
     */
    public static void userLogout() {
        LoginUser loginUser = getLoginUser();
        if (loginUser != null) {
            LOGIN_USER_INFO.remove(loginUser.getUserId());
        }
    }

    /**
     * 判断用户是否登录
     *
     * @param logout 是否登出
     * @return 是否登录
     */
    public static boolean isLogin(boolean logout) {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return false;
        }
        if (loginUser.getExpireTime() == null || loginUser.getExpireTime().before(DateUtil.date())) {
            if (logout) {
                userLogout();
            }
            return false;
        }
        return true;
    }

}

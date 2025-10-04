package com.dolokey.dkblog.config;

import com.dolokey.dkblog.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * 启动初始化执行器
 *
 * @author dolokey
 * @date 2025/10/03
 */
@Slf4j
@Configuration
public class DkAppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("管理员用户初始化开始");
        try {
            TokenUtil.initAdminUser();
        } catch (Exception e) {
            log.error("管理员用户初始化异常：{}", e.getMessage());
            throw e;
        }
        log.info("管理员用户初始化完成，管理员用户：{}", TokenUtil.getAdminUser());
    }
}

/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.model.User;

import java.util.List;

/**
 * 用户接口
 *
 * @author chenjinyao
 * @date 2025/09/22
 */
public interface IUserService {

    Page<User> list(Page<User> page);
}

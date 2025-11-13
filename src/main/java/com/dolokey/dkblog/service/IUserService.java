package com.dolokey.dkblog.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.entity.dto.UserDTO;
import com.dolokey.dkblog.entity.exception.ServiceException;
import com.dolokey.dkblog.entity.exception.ValidationException;
import com.dolokey.dkblog.model.User;

import java.util.List;

/**
 * 用户接口
 *
 * @author dolokey
 * @date 2025/09/22
 */
public interface IUserService {

    List<User> list(UserDTO searchBean, Page<User> page);

    Long save(UserDTO userDTO) throws ValidationException, ServiceException;

    Long update(UserDTO userDTO) throws ValidationException, ServiceException;

    User findByUsername(String username);
}

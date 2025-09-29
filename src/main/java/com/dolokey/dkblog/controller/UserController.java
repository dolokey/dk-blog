/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.entity.api.MR;
import com.dolokey.dkblog.entity.api.R;
import com.dolokey.dkblog.entity.dto.UserDTO;
import com.dolokey.dkblog.entity.exception.ServiceException;
import com.dolokey.dkblog.entity.exception.ValidationException;
import com.dolokey.dkblog.entity.vo.UserVO;
import com.dolokey.dkblog.model.User;
import com.dolokey.dkblog.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制层
 *
 * @author chenjinyao
 * @date 2025/09/22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/list")
    public MR<UserVO> list(UserDTO searchBean, Page<User> page) {
        List<User> list = userService.list(searchBean, page);
        page.setRecords(list);
        return MR.data(page.convert(user -> BeanUtil.toBean(user, UserVO.class)));
    }

    @PostMapping("/save")
    public R<Long> save(UserDTO userDTO) throws ValidationException, ServiceException {
        Long id = userService.save(userDTO);
        return R.data(id);
    }

    @PostMapping("/update")
    public R<Void> update(UserDTO userDTO) throws ValidationException, ServiceException {
        userService.update(userDTO);
        return R.success();
    }
}

package com.dolokey.dkblog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.entity.api.MR;
import com.dolokey.dkblog.entity.api.R;
import com.dolokey.dkblog.entity.dto.LogDTO;
import com.dolokey.dkblog.entity.security.ServeRight;
import com.dolokey.dkblog.entity.vo.LogVO;
import com.dolokey.dkblog.enums.ServeRightType;
import com.dolokey.dkblog.model.Log;
import com.dolokey.dkblog.service.ILogService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 日志控制层
 *
 * @author dolokey
 * @date 2025/11/13
 */
@RestController
@RequestMapping("/log")
@ServeRight(type = ServeRightType.ADMIN)
public class LogController {

    @Resource
    private ILogService logService;

    @GetMapping("/list")
    public MR<LogVO> list(LogDTO searchBean, Page<Log> page) {
        List<Log> list = logService.list(searchBean, page);
        page.setRecords(list);
        return MR.data(page.convert(user -> BeanUtil.toBean(user, LogVO.class)));
    }

    @GetMapping("typeList")
    public R<Map<String, String>> typeList() {
        return R.data(logService.typeList());
    }
}

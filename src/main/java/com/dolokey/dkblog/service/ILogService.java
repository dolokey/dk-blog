package com.dolokey.dkblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.entity.dto.LogDTO;
import com.dolokey.dkblog.model.Log;

import java.util.List;
import java.util.Map;

/**
 * 日志接口
 *
 * @author dolokey
 * @date 2025/10/04
 */
public interface ILogService {

    /**
     * 保存日志
     *
     * @param logDTO 日志数据传输对象
     * @return 日志编号
     */
    Long save(LogDTO logDTO);

    Map<String, String> typeList();

    List<Log> list(LogDTO searchBean, Page<Log> page);
}

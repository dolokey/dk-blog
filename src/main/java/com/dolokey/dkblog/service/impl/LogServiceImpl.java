package com.dolokey.dkblog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.component.security.SystemLogAspect;
import com.dolokey.dkblog.entity.dto.LogDTO;
import com.dolokey.dkblog.entity.security.LoginUser;
import com.dolokey.dkblog.enums.LogOperateResult;
import com.dolokey.dkblog.mapper.LogMapper;
import com.dolokey.dkblog.model.Log;
import com.dolokey.dkblog.model.User;
import com.dolokey.dkblog.service.ILogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志接口实现类
 *
 * @author dolokey
 * @date 2025/10/04
 */
@Service
public class LogServiceImpl implements ILogService {

    public LogServiceImpl() {
        LOG_TYPE_MAP.put(User.class.getSimpleName(), "用户");
        LOG_TYPE_MAP.put(LoginUser.class.getSimpleName(), "登录情况");
    }

    private static final Map<String, String> LOG_TYPE_MAP = new HashMap<>();

    @Resource
    private LogMapper logMapper;

    /**
     * 保存日志
     *
     * @param logDTO 日志数据传输对象
     * @return 日志编号
     */
    @Override
    public Long save(LogDTO logDTO) {
        Log log = BeanUtil.toBean(logDTO, Log.class);
        Date startTime = SystemLogAspect.getLogStartTime();
        log.setStartTime(startTime);
        log.setCostTime(DateUtil.date().getTime() - startTime.getTime());
        boolean error = CharSequenceUtil.isNotBlank(log.getErrorMsg());
        log.setResult(error ? LogOperateResult.FAIL : LogOperateResult.SUCCESS);
        return logMapper.save(log);
    }

    @Override
    public Map<String, String> typeList() {
        return LOG_TYPE_MAP;
    }

    @Override
    public List<Log> list(LogDTO searchBean, Page<Log> page) {
        LambdaQueryWrapper<Log> queryWrapper = new LambdaQueryWrapper<Log>()
                .eq(CharSequenceUtil.isNotBlank(searchBean.getType()), Log::getType, searchBean.getType())
                .orderByDesc(Log::getCrTime);
        return logMapper.selectList(page, queryWrapper);
    }
}

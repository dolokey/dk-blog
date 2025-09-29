/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.mapper;


import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dolokey.dkblog.entity.common.CoreEntity;
import com.dolokey.dkblog.entity.exception.ServiceException;
import org.slf4j.LoggerFactory;

/**
 * 基础服务实现类
 *
 * @author chenjinyao
 * @date 2025/09/29
 */
public interface BaseServiceMapper<T extends CoreEntity> extends BaseMapper<T> {

    default T findById(String id) throws ServiceException {
        if (CharSequenceUtil.isBlank(id)) {
            throw new ServiceException("对象[" + this.getClass().getName() + "]查询时未传入编号");
        }
        if (!CharSequenceUtil.isNumeric(id)) {
            throw new ServiceException("对象[" + this.getClass().getName() + "]编号查询时传入编号[" + id + "]不符合规范");
        }
        return selectById(Long.valueOf(id));
    }

    default T findByIdThrowEx(String id) throws ServiceException {
        T obj = findById(id);
        if (obj == null) {
            throw new ServiceException("编号[" + id + "]的对象[" + this.getClass().getName() + "]不存在");
        }
        return obj;
    }

    default int delete(String ids) throws ServiceException {
        if (CharSequenceUtil.isBlank(ids)) {
            throw new ServiceException("对象[" + this.getClass().getName() + "]删除时未传入编号");
        }
        LoggerFactory.getLogger(this.getClass()).info("删除对象[{}]编号[{}]", this.getClass().getName(), ids);
        return deleteByIds(CoreEntity.converts(ids));
    }
}

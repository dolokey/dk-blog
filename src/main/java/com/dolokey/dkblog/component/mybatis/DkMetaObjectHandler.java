/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.component.mybatis;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 元数据处理器<br>
 * 实现用于自动填充创建用户及更新用户
 *
 * @author chenjinyao
 * @date 2025/09/22
 */
@Slf4j
@Component
public class DkMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时设置默认值 填充创建时间和更新时间（需要判断更新时间时，仅需要判断更新时间，不需要考虑空值）
        Date currentTime = new Date();
        this.strictInsertFill(metaObject, "crTime", Date.class, currentTime);
        this.strictInsertFill(metaObject, "upTime", Date.class, currentTime);
        // 临时（后续改为成）
        this.strictInsertFill(metaObject, "crUser", String.class, "dolphin");
        this.strictInsertFill(metaObject, "upUser", String.class, "dolphin");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "upTime", Date.class, new Date());
        // 临时
        this.strictInsertFill(metaObject, "upUser", String.class, "dolphin");
    }
}

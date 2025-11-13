package com.dolokey.dkblog.entity.vo;

import com.dolokey.dkblog.enums.LogOperateResult;
import lombok.Data;

import java.util.Date;

/**
 * 日志视图
 *
 * @author dolokey
 * @date 2025/11/13
 */
@Data
public class LogVO {


    /**
     * 操作类型
     */
    private String type;

    /**
     * 操作对象
     */
    private Long object;

    /**
     * 操作描述
     */
    private String operateDesc;

    /**
     * 操作结果
     */
    private LogOperateResult result;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 操作耗时
     */
    private Long costTime;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 结果说明
     */
    private String errorMsg;

    /**
     * 创建用户
     */
    private String crUser;

    /**
     * 创建时间
     */
    private Date crTime;
}

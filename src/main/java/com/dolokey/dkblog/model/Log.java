package com.dolokey.dkblog.model;


import com.dolokey.dkblog.entity.common.CoreEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志
 *
 * @author dolokey
 * @date 2025/09/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Log extends CoreEntity {

    /**
     * 操作类型
     */
    private String type;

    /**
     * 操作对象
     */
    private String object;

    /**
     * 操作描述
     */
    private String desc;

    /**
     * 操作结果
     */
    private Integer result;

    /**
     * 操作耗时
     */
    private Log costTime;

    /**
     * 请求参数
     */
    private String params;

}

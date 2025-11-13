package com.dolokey.dkblog.entity.dto;

import lombok.Data;

/**
 * 日志数据传输对象
 *
 * @author dolokey
 * @date 2025/10/04
 */
@Data
public class LogDTO {

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
     * 请求参数
     */
    private String params;

    /**
     * 结果说明
     */
    private String errorMsg;


}

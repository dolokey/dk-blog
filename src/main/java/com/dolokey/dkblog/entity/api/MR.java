/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dolokey.dkblog.enums.ResultCode;
import lombok.Data;

import java.util.List;

/**
 * 批量接口返回对象
 *
 * @author chenjinyao
 * @date 2025/09/15
 */
@Data
public class MR<T> {

    private MR(Page<T> page) {
        this(ResultCode.SUCCESS.getCode(), page, ResultCode.SUCCESS.getMessage());
    }


    private MR(int code, Page<T> data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.error = ResultCode.FAILURE.getCode() == code;
    }

    public static <T> MR<T> data(Page<T> page) {
        return new MR<>(page);
    }

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 是否异常
     */
    private boolean error;

    /**
     * 数据
     */
    private Page<T> data;

}

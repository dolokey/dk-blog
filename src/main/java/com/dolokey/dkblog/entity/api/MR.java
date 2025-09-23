/*
 * @Copyright © FUJIAN TERTON SOFTWARE CO., LTD
 */


package com.dolokey.dkblog.entity.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dolokey.dkblog.enums.ResultCode;
import lombok.Data;

/**
 * 批量接口返回对象
 *
 * @author chenjinyao
 * @date 2025/09/15
 */
@Data
public class MR<T> {

    private MR(IPage<T> page) {
        this(ResultCode.SUCCESS.getCode(), page, ResultCode.SUCCESS.getMessage());
    }


    private MR(int code, IPage<T> data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.error = ResultCode.FAILURE.getCode() == code;
    }

    public static <T> MR<T> data(IPage<T> page) {
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
    private IPage<T> data;

}

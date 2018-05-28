package com.web.machineversion.model.OV;

import com.web.machineversion.model.ResultCode;
import lombok.Data;

@Data
public class Result<T> {
    /**
     * 标识码
     */
    private ResultCode code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

}

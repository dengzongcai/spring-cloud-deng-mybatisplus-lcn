package com.deng.commons.config.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回结果类
 * @param <T>
 */
@Data
@ApiModel(value = "返回结果封装类")
public class ResultVo<T> {

    @ApiModelProperty(value = "错误码")
    private Integer code;

    @ApiModelProperty(value = "描述")
    private String message;

    @ApiModelProperty(value = "请求结果 0失败 1成功")
    private Integer Status;

    @ApiModelProperty(value = "接口返回数据")
    private T data;
}

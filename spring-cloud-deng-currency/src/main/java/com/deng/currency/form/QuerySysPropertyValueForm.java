package com.deng.currency.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther ZongCai
 * @Date 2021/8/22
 */
@Data
@ApiModel(value = "获取配置信息VALUE")
public class QuerySysPropertyValueForm {
    @ApiModelProperty(value = "配置信息KEY")
    private String key;
}

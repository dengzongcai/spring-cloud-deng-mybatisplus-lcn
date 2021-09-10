package com.deng.currency.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther ZongCai
 * @Date 2021/7/13
 */
@Data
@ApiModel(value = "地区查询条件")
public class SysAreaForm {
    @ApiModelProperty(value = "省id")
    private Long provinceId;

    @ApiModelProperty(value = "市id")
    private Long cityId;

    @ApiModelProperty(value = "县/区id")
    private Long areaId;
}

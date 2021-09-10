package com.deng.commons.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysAreaVO {
    @ApiModelProperty(value = "地区父id")
    private Long parentId;

    @ApiModelProperty(value = "城市Code")
    private String areaCode;

    @ApiModelProperty(value = "名称")
    private String areaName;

}

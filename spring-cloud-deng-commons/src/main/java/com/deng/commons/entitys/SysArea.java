package com.deng.commons.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_area")
@ApiModel(value="地区表",description="地区表")
public class SysArea extends FullAuditedEntity {
    @ApiModelProperty(value = "地区父id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String areaName;

    @ApiModelProperty(value = "排序")
    private Integer areaOrderBy;

    @ApiModelProperty(value = "地区名称拼音")
    private String areaNameWithSpell;

    @ApiModelProperty(value = "城市Code")
    private String areaCode;

}

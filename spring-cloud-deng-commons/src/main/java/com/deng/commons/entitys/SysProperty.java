package com.deng.commons.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sys_property")
@ApiModel(value="配置信息表",description="配置信息表")
public class SysProperty extends FullAuditedEntity {
    @ApiModelProperty(value = "配置信息KEY")
    private String key;

    @ApiModelProperty(value = "配置信息VALUE")
    private String values;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "状态：1.可用  2.禁用")
    private Integer status;

}

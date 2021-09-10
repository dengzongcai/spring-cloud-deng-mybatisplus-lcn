package com.deng.commons.common.qichacha.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OriginalName {
    @ApiModelProperty(value = "曾用名")
    private String Name;
    @ApiModelProperty(value = "变更日期")
    private String ChangeDate;

}

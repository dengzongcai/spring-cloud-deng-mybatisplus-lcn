package com.deng.commons.forms.synclog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther ZongCai
 * @Date 2021/7/8
 */
@Data
@ApiModel(value = "日志查询条件")
public class BaseSyncLogForm {
    @ApiModelProperty(value = "主键（编辑必传）")
    private Long id;
    @ApiModelProperty(value = "时间")
    private String newData;
    @ApiModelProperty(value = "操作人")
    private String operationPerson;
}

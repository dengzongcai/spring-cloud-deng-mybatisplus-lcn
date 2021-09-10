package com.deng.quartz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther ZongCai
 * @Date 2021/7/14
 */

@Data
@ApiModel(value = "添加/编辑定时任务")
public class ScheduledForm {
    @ApiModelProperty(value = "主键(编辑时必传)")
    private Long id;

    @ApiModelProperty(value = "任务key值（使用bean名称）")
    private String taskKey;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务表达式")
    private String taskCron;

    @ApiModelProperty(value = "状态：0.禁用 1.启用")
    private Integer taskStatus;
}

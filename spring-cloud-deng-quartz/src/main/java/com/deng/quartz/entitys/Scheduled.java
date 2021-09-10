package com.deng.quartz.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import com.deng.commons.entitys.FullAuditedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 定时任务配置表
 *
 * @Auther ZongCai
 * @Date 2021/7/16
 */
@Data
@TableName("scheduled")
public class Scheduled extends FullAuditedEntity {
    @ApiModelProperty(value = "任务key值（使用bean名称）")
    private String taskKey;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务表达式")
    private String taskCron;

    @ApiModelProperty(value = "状态：0.禁用 1.启用")
    private Integer taskStatus;

}

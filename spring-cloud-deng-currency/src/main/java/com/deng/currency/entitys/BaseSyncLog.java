package com.deng.currency.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import com.deng.commons.entitys.FullAuditedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
@TableName("base_sync_log")
public class BaseSyncLog extends FullAuditedEntity {
    @ApiModelProperty(value = "时间")
    private Date newData;
    @ApiModelProperty(value = "操作人")
    private String operationPerson;

}

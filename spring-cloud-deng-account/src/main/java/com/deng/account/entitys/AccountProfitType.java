package com.deng.account.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import com.deng.commons.entitys.FullAuditedEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * account_profit_type
 * @author 
 */
@ApiModel(value="账户收益/支出类型表")
@Data
@TableName("account_profit_type")
public class AccountProfitType extends FullAuditedEntity {
    @ApiModelProperty(value="收益/支出类型描述")
    private String profitTypeDesc;

    @ApiModelProperty(value="状态 0.启用   1.未启用")
    private Integer status;

}
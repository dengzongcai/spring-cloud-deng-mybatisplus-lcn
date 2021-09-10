package com.deng.account.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import com.deng.commons.entitys.FullAuditedEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * account_type
 * @author 
 */
@ApiModel(value="账户类型表")
@Data
@TableName("account_type")
public class AccountType extends FullAuditedEntity {
    @ApiModelProperty(value="账户类型描述")
    private String accountTypeDesc;

    @ApiModelProperty(value="状态 0.启用   1.未启用")
    private Integer status;

    @ApiModelProperty(value="备注")
    private String remark;

}
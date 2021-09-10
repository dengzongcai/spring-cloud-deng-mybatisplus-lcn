package com.deng.account.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import com.deng.commons.entitys.FullAuditedEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * account_type_and_profit_type_relation
 * @author 
 */
@ApiModel(value="账户类型与收益类型关系表")
@Data
@TableName("account_type_and_profit_type_relation")
public class AccountTypeAndProfitTypeRelation extends FullAuditedEntity {
    @ApiModelProperty(value="账户系统角色id")
    private Long accountSystemRoleId;

    @ApiModelProperty(value="账户类型id")
    private Long accountTypeId;

    @ApiModelProperty(value="账户收益类型id")
    private Long accountProfitTypeId;

    @ApiModelProperty(value="备注")
    private String remark;

}
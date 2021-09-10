package com.deng.account.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import com.deng.commons.entitys.FullAuditedEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * account_system_role_and_account_type_relation
 * @author 
 */
@ApiModel(value="账户系统角色与账户关系表")
@Data
@TableName("account_system_role_and_account_type_relation")
public class AccountSystemRoleAndAccountTypeRelation extends FullAuditedEntity {
    @ApiModelProperty(value="账户系统角色id")
    private Long systemRoleId;

    @ApiModelProperty(value="账户类型id")
    private Long accountTypeId;

    @ApiModelProperty(value="备注")
    private String remark;

}
package com.deng.account.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import com.deng.commons.entitys.FullAuditedEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * account_system_role
 * @author 
 */
@ApiModel(value="账户系统角色表")
@Data
@TableName("account_system_role")
public class AccountSystemRole extends FullAuditedEntity {
    @ApiModelProperty(value="账户类型描述")
    private String roleTypeDesc;

    @ApiModelProperty(value="状态 0.启用   1.未启用")
    private Integer status;

    @ApiModelProperty(value="备注")
    private String remark;

}
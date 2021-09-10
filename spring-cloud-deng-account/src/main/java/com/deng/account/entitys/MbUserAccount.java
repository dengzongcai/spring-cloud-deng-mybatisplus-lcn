package com.deng.account.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import com.deng.commons.entitys.FullAuditedEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * mb_user_account
 * @author
 */
@ApiModel(value="用户(实体)账户表")
@Data
@Accessors(chain = true)
@TableName("mb_user_account")
public class MbUserAccount extends FullAuditedEntity {
    @ApiModelProperty(value="实体用户id")
    private Long userId;

    @ApiModelProperty(value="账户类型id")
    private Long accountTypeId;

    @ApiModelProperty(value="账户余额")
    private BigDecimal accountBalance;

    @ApiModelProperty(value="状态 0.启用   1.未启用")
    private Integer status;

    @ApiModelProperty(value="上次操作对象")
    private String lastOperationObject;

    @ApiModelProperty(value="上次交易余额")
    private BigDecimal lastTransBalance;

    @ApiModelProperty(value="备注")
    private String remark;

}
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
 * mb_user_account_detail
 * @author 
 */
@ApiModel(value="用户(实体)账户明细表")
@Data
@Accessors(chain = true)
@TableName("mb_user_account_detail")
public class MbUserAccountDetail extends FullAuditedEntity {
    @ApiModelProperty(value="实体用户id")
    private Long userId;

    @ApiModelProperty(value="账户类型id")
    private Long accountTypeId;

    @ApiModelProperty(value="收支类型 1.收入  2.支出")
    private Integer incomePayType;

    @ApiModelProperty(value="收支额度")
    private BigDecimal incomePayQuota;

    @ApiModelProperty(value="收支方向账户id")
    private Long incomePayDirectionAccountId;

    @ApiModelProperty(value="收支方向明细id")
    private Long incomePayDirectionDetailId;

    @ApiModelProperty(value="账户收益类型id")
    private Long accountProfitTypeId;

    @ApiModelProperty(value="本次交易余额")
    private BigDecimal thisTransBalance;

    @ApiModelProperty(value="上次交易余额")
    private BigDecimal lastTransBalance;

    @ApiModelProperty(value="本次操作对象")
    private String thisOperationObject;

    @ApiModelProperty(value="来源id")
    private String belongId;

    @ApiModelProperty(value="来源类型")
    private String belongType;

    @ApiModelProperty(value="备注")
    private String remark;

}
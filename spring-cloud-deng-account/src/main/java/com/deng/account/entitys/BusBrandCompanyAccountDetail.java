package com.deng.account.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import com.deng.commons.entitys.FullAuditedEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * bus_brand_company_account_detail
 * @author 
 */
@ApiModel(value="公司账户明细表")
@Data()
@Accessors(chain = true)
@TableName("bus_brand_company_account_detail")
public class BusBrandCompanyAccountDetail extends FullAuditedEntity {
    @ApiModelProperty(value="品牌公司id")
    private Long brandCompanyId;

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

    @ApiModelProperty(value="本地交易余额")
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
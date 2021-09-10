package com.deng.commons.common.qichacha.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class Result {
    @ApiModelProperty(value = "内部KeyNo")
    private String KeyNo;
    @ApiModelProperty(value = "公司名称")
    private String Name;
    @ApiModelProperty(value = "注册号")
    private String No;
    @ApiModelProperty(value = "登记机关")
    private String BelongOrg;
    @ApiModelProperty(value = "法人名")
    private String OperName;
    @ApiModelProperty(value = "成立日期")
    private String StartDate;
    @ApiModelProperty(value = "吊销日期")
    private String EndDate;
    @ApiModelProperty(value = "企业状态")
    private String Status;
    @ApiModelProperty(value = "省份")
    private String Province;
    @ApiModelProperty(value = "更新日期")
    private String UpdatedDate;
    @ApiModelProperty(value = "社会统一信用代码")
    private String CreditCode;
    @ApiModelProperty(value = "注册资本")
    private String RegistCapi;
    @ApiModelProperty(value = "企业类型")
    private String EconKind;
    @ApiModelProperty(value = "地址")
    private String Address;
    @ApiModelProperty(value = "经营范围")
    private String Scope;
    @ApiModelProperty(value = "营业开始日期")
    private String TermStart;
    @ApiModelProperty(value = "营业开始日期")
    private String TeamEnd;
    @ApiModelProperty(value = "发照日期")
    private String CheckDate;
    @ApiModelProperty(value = "组织机构代码")
    private String OrgNo;
    @ApiModelProperty(value = "是否IPO上市(0为未上市，1为上市)")
    private String IsOnStock;
    @ApiModelProperty(value = "上市公司代码")
    private String StockNumber;
    @ApiModelProperty(value = "上市类型")
    private String StockType;
    @ApiModelProperty(value = "曾用名")
    private List<OriginalName> OriginalName;
    @ApiModelProperty(value = "")
    private String ImageUrl;
    @ApiModelProperty(value = "企业类型，0-公司，1-社会组织 ，3-香港公司，4-事业单位，6-基金会，7-医院，8-海外公司，9-律师事务所，10-学校 ，-1-其他")
    private String EntType;
    @ApiModelProperty(value = "实缴资本")
    private String RecCap;

}

package com.deng.commons.entitys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息表
 * @author
 * @date 2020-10-12 13:11:05
 */

@Data
@ApiModel(value="用户信息表",description="用户信息表")
public class MbUser {

    @ApiModelProperty(value = "会员id")
    private Long userid;

    @ApiModelProperty(value = "昵称")
    private String usernick;

    @ApiModelProperty(value = "手机")
    private String usermobile;

    @ApiModelProperty(value = "登录密码")
    private String userpassword;

    @ApiModelProperty(value = "安全密码")
    private String securepassword;

    @ApiModelProperty(value = "用户头像")
    private String userpic;

    @ApiModelProperty(value = "性别")
    private Short usersex;

    @ApiModelProperty(value = "注册时间")
    private Date userregisttime;

    @ApiModelProperty(value = "用户等级")
    private Short usergrade;

    @ApiModelProperty(value = "营业额标注")
    private BigDecimal usertotalintegral;

    @ApiModelProperty(value = "账户余额")
    private BigDecimal usersurplusintegral;

    @ApiModelProperty(value = "登录IP")
    private String userip;

    @ApiModelProperty(value = "登录时间")
    private Date usercurrentlogintime;

    @ApiModelProperty(value = "上级id")
    private Long usersuperiorid;

    @ApiModelProperty(value = "环信用户ID")
    private String easemobuuid;

    @ApiModelProperty(value = "信息提示: 0、不提示 1、提示 ")
    private Short infotips;

    @ApiModelProperty(value = "是否禁止登录: 0、未禁止 1、禁止 ")
    private Short userban;

    @ApiModelProperty(value = "是否删除: 0、未删除 1、删除")
    private Short userdel;

    @ApiModelProperty(value = "Easemobid")
    private String easemobid;

    @ApiModelProperty(value = "是否新用户: 0.是  1.不是")
    private Integer isnew;

    @ApiModelProperty(value = "InvitationCode")
    private String invitationcode;

    @ApiModelProperty(value = "UserVip")
    private Integer uservip;

    @ApiModelProperty(value = "0 :没有实体店  1：初级实体店  2 高级实体店")
    private Integer userentitytype;

    @ApiModelProperty(value = "开发类型：0.无邀请码实体注册  2.无邀请码VIP注册  3.有(实体)邀请码VIP注册 4.有(实体)邀请码实体注册  5.有(VIP)邀请码VIP注册 6.孵化用户 7.孵化用户邀请用户")
    private Integer developmenttype;

    @ApiModelProperty(value = "用户类型：0.实体用户 1.VIP用户")
    private Integer usertype;

    @ApiModelProperty(value = "")
    private String invitecode;

    @ApiModelProperty(value = "是否为法人本人 0:是 1:不是")
    private Integer islegalperson;

    @ApiModelProperty(value = "企业名称")
    private String enterprisename;

    @ApiModelProperty(value = "法人代表")
    private String egalpersonname;

    @ApiModelProperty(value = "注册号")
    private String registrationnumber;

    @ApiModelProperty(value = "营业执照图片")
    private String businesslicense;

    @ApiModelProperty(value = "授权证书")
    private String authorizationcertificate;

    @ApiModelProperty(value = "身份证正面")
    private String identitycardpositive;

    @ApiModelProperty(value = "身份证反面")
    private String identitycardback;

    @ApiModelProperty(value = "当用户是孵化用户时：是否是虚拟平台合伙人 0否 1 是 ")
    private Integer isfictitious;

    @ApiModelProperty(value = "代理商品业绩")
    private BigDecimal commodityperformance;

    @ApiModelProperty(value = "非代理商品业绩")
    private BigDecimal nonagencyachievement;

    @ApiModelProperty(value = "无限制账户")
    private BigDecimal unlimitedaccount;

    @ApiModelProperty(value = "货款专用账户")
    private BigDecimal limitaccount;

    @ApiModelProperty(value = "结算账户")
    private BigDecimal settlementaccount;

    @ApiModelProperty(value = "预结算账户")
    private BigDecimal presettlementaccount;

    @ApiModelProperty(value = "管家")
    private String childcardno;

    @ApiModelProperty(value = "扣税总额")
    private BigDecimal taxtotal;

    @ApiModelProperty(value = "返税总额")
    private BigDecimal taxrebatetotal;

    @ApiModelProperty(value = "可返税总额")
    private BigDecimal refundabletax;

    @ApiModelProperty(value = "店铺起购金额")
    private BigDecimal buyfromquota;
}

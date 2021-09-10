package com.deng.auth.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * @Auther ZongCai
 * @Date 2021/7/13
 */
@Data
public class GetTokenForm {
    @ApiModelProperty(value = "授权模式")
    @NonNull
    private String grantType;

    @ApiModelProperty(value = "Oauth2客户端ID")
    @NonNull
    private String clientId;

    @ApiModelProperty(value = "Oauth2客户端秘钥")
    @NonNull
    private String clientSecret;

    @ApiModelProperty(value = "账户类型 1.后台登录 2 app登录,4 VipApp登录")
    @NonNull
    private String accountType;

    @ApiModelProperty(value = "登陆类型 1：手机号密码登陆 2：验证码登陆")
    @NonNull
    private String appType;

    @ApiModelProperty(value = "刷新token")
    private String refreshToken;

    @ApiModelProperty(value = "登录用户名")
    private String userName;

    @ApiModelProperty(value = "登录密码")
    private String passWord;

}

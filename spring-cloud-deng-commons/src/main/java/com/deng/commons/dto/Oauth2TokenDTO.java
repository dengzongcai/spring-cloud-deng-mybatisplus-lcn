package com.deng.commons.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Map;

/**
 * @Description: Oauth2获取Token返回信息封装
 * @Auther ZongCai
 * @Date 2021/7/13
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Oauth2TokenDTO {
    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("刷令牌")
    private String refreshToken;
    @ApiModelProperty("访问令牌头前缀")
    private String tokenHead;
    @ApiModelProperty("有效时间（秒）")
    private int expiresIn;

    private Long userid;
    private Map<String, String> map ;
}

package com.deng.gateway.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录用户信息
 * Created by goldendays on 2020/6/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDto {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("客户端ID")
    private String clientId;
    @ApiModelProperty("用户权限")
    private List<String> roles;

}

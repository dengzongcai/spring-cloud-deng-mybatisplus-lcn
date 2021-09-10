package com.deng.gateway.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Swagger自定义配置
 * Created by goldendays on 2020/7/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SwaggerProperties {
    @ApiModelProperty(value = "API文档生成基础路径")
    private String apiBasePackage;
    @ApiModelProperty(value = "是否要启用登录认证")
    private boolean enableSecurity;
    @ApiModelProperty(value = "文档标题")
    private String title;
    @ApiModelProperty(value = "文档描述")
    private String description;
    @ApiModelProperty(value = "文档版本")
    private String version;
    @ApiModelProperty(value = "文档联系人姓名")
    private String contactName;
    @ApiModelProperty(value = "文档联系人网址")
    private String contactUrl;
    @ApiModelProperty(value = "文档联系人邮箱")
    private String contactEmail;
}

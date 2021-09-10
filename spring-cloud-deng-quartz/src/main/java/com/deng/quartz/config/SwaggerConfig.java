package com.deng.quartz.config;

import com.deng.commons.swagger.BaseSwaggerConfig;
import com.deng.commons.swagger.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @ClassName: SwaggerConfig
 * @Description: Swagger API文档相关配置
 * @author ZongCai
 * @date 2021/7/8
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.deng.quartz.controller")
                .title("【鄧】")
                .description("定时任务相关接口文档")
                .contactName("deng")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}

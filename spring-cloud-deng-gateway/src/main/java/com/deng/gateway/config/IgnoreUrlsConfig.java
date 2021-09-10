package com.deng.gateway.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * @ClassName: IgnoreUrlsConfig 
 * @Description: 网关白名单配置
 * @Auther ZongCai
 * @Date 2021/7/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="secure.ignore")
public class IgnoreUrlsConfig {
    private List<String> urls;
}

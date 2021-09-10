package com.deng.quartz;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

// 扫描dao层目录
@MapperScan("com.deng.quartz.mapper")
@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
// 开启分布式事务(表明这是一个txmanager的client)
@EnableDistributedTransaction
public class QuartzApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(QuartzApplication.class);
    }
}

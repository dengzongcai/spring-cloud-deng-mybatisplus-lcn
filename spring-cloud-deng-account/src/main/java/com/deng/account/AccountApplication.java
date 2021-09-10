package com.deng.account;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 扫描dao层目录
@MapperScan("com.deng.account.mapper")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
// 开启分布式事务(表明这是一个txmanager的client)
@EnableDistributedTransaction
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}

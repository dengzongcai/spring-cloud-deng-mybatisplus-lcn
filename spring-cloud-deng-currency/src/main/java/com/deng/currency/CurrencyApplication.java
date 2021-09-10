package com.deng.currency;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 扫描dao层目录
@MapperScan("com.deng.currency.mapper")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
// 开启分布式事务(表明这是一个txmanager的client)
@EnableDistributedTransaction
public class CurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyApplication.class, args);
    }

}

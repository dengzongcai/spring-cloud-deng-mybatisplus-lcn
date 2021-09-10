package com.deng.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     *
     * @Title: globalExceptionHandler
     * @Description: 全局异常处理初始化
     * @param @return 参数说明
     * @return GlobalExceptionHandler 返回类型
     * @throws
     */
    @RequestMapping("/forwardTest")
    public String forwardTest(){
        return "timeout!";
    }
}

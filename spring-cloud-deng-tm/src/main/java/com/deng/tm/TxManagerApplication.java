package com.deng.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TX-LCN服务(运行服务，自动创建数据库表)
 *  --TX-LCN分布式事务，参考：https://blog.csdn.net/qq_43173523/article/details/106408473
 *  运行服务后，访问服务地址(本地：localhost):9005(服务端口) 密码:123456(配置文件：tx-lcn.manager.admin-key)
 *
 * @Auther ZongCai
 * @Date 2021/9/9
 */
@SpringBootApplication
// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven /> (表明这是一个txmanager的服务)
@EnableTransactionManagerServer
public class TxManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxManagerApplication.class, args);
    }

}

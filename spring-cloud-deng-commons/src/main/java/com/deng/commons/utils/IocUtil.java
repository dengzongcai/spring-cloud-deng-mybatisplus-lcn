package com.deng.commons.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean注入Spring容器（手动注入Spring容器）
 *
 * @Auther ZongCai
 * @Date 2021/7/30
 */
public class IocUtil {
    public static ClassPathXmlApplicationContext context(String beanXml) {
        //启动spring容器
        return new ClassPathXmlApplicationContext(beanXml);
    }

    public static void main(String[] args) {
        String beanXml = "classpath:/com/deng/commons/xml/test.xml";
        System.out.println("spring容器启动中...");
        ClassPathXmlApplicationContext context = IocUtil.context(beanXml);
        System.out.println("spring容器启动完毕...");
        //从容器中开始查找sysarea Bean
        System.out.println(context.getBean("sysarea"));
    }
}
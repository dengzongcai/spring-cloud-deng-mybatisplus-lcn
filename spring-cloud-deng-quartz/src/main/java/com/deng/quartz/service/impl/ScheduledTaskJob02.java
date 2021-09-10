package com.deng.quartz.service.impl;

import com.deng.quartz.service.ScheduledTaskJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description 测试类02
 * @Auther ZongCai
 * @Date 2021/7/16
 */
@Service
@Slf4j
public class ScheduledTaskJob02 implements ScheduledTaskJob {
//    @Autowired
//    private CurrentyFeignService currentyfeignservice;

    @Override
    public void run() {
        // TODO 要处理的业务逻辑
        //currentyfeignservice.testTask02();
        log.info("ScheduledTask => 02  run  当前线程名称 {} ", Thread.currentThread().getName());
    }
}

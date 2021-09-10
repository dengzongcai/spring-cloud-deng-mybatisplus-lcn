package com.deng.quartz.config;

import com.deng.quartz.service.ScheduledTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 项目启动完毕后开启需要自启的任务
 *
 * @Auther ZongCai
 * @Date 2021/7/16
 */
@Slf4j
@Component
public class ScheduledTaskRunner implements ApplicationRunner {

    @Resource
    private ScheduledTaskService scheduledTaskService;

    /**
     * 程序启动完毕后,需要自启的任务
     */
    @Override
    public void run(ApplicationArguments applicationArguments){
        log.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 开始!");
        scheduledTaskService.initAllTask();
        log.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 结束！");
    }
}

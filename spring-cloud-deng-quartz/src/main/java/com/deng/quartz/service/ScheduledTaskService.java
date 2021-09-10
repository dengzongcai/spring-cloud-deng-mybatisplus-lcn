package com.deng.quartz.service;


import com.baomidou.mybatisplus.service.IService;
import com.deng.quartz.entitys.Scheduled;

/**
 * @Description 定时任务接口
 * @Auther ZongCai
 * @Date 2021/7/16
 */
public interface ScheduledTaskService extends IService<Scheduled> {

    /**
     * 根据任务key 启动任务
     */
    Boolean start(String taskKey, Scheduled scheduled);

    /**
     * 根据任务key 停止任务
     */
    Boolean stop(String taskKey);

    /**
     * 根据任务key 重启任务
     */
    Boolean restart(String taskKey, Scheduled scheduled);

    /**
     * 初始化  ==> 启动所有正常状态的任务
     */
    void initAllTask();
}
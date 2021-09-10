package com.deng.quartz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.deng.commons.utils.SpringContextUtil;
import com.deng.quartz.enums.ScheduledStatus;
import com.deng.quartz.mapper.ScheduledMapper;
import com.deng.quartz.service.ScheduledTaskJob;
import com.deng.quartz.service.ScheduledTaskService;
import com.deng.quartz.entitys.Scheduled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 定时任务实现
 * @Auther ZongCai
 * @Date 2021/7/16
 */
@Service
@Transactional
@Import({SpringContextUtil.class})
@Slf4j
public class ScheduledTaskServiceImpl extends ServiceImpl<ScheduledMapper, Scheduled> implements ScheduledTaskService {

    @Value("${task.enabled}")
    private Boolean taskEnable;

    /**
     * 可重入锁
     */
    private ReentrantLock lock = new ReentrantLock();
    /**
     * 定时任务线程池
     */
    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 存放已经启动的任务map
     */
    private Map<String, ScheduledFuture> scheduledFutureMap = new ConcurrentHashMap<>();

    @Resource
    private ScheduledMapper scheduledMapper;

    /**
     * 描述: 根据任务key 启动任务
     *
     * @param taskKey
     */
    @Override
    public Boolean start(String taskKey, Scheduled scheduled) {
        log.info(">>>>>> 启动任务 {} 开始 >>>>>>", taskKey);
        //添加锁放一个线程启动，防止多人启动多次
        lock.lock();
        log.info(">>>>>> 添加任务启动锁完毕");
        try {
            //校验是否已经启动
            if (this.isStart(taskKey)) {
                log.info(">>>>>> 当前任务已经启动，无需重复启动！");
                return false;
            }
            if(scheduled == null){
                //查询配置
                scheduled = this.getByTaskKey(taskKey);
            }
            if(scheduled == null){
                return false;
            }
            if (!scheduled.getTaskStatus().equals(ScheduledStatus.DISABLE.getCode())){
                scheduled.setTaskStatus(ScheduledStatus.DISABLE.getCode());
                this.updateById(scheduled);
            }
            //启动任务
            this.doStartTask(scheduled);
        } finally {
            // 释放锁
            lock.unlock();
            log.info(">>>>>> 释放任务启动锁完毕");
        }
        log.info(">>>>>> 启动任务 {} 结束 >>>>>>", taskKey);
        return true;
    }

    /**
     * 描述: 查询定时任务配置参数
     *
     * @param taskKey
     * @return com.yihaocard.main.module.scheduled.model.Scheduled
     * @author lv617
     * @date 2020/9/24 11:14
     */
    private Scheduled getByTaskKey(String taskKey) {
        EntityWrapper<Scheduled> wrapper = new EntityWrapper<>();
        //wrapper.eq("status", ScheduledStatus.DISABLE.getCode());
        wrapper.eq("task_key",taskKey);
        List<Scheduled> scheduleds = scheduledMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(scheduleds)){
            return null;
        }
        return scheduleds.get(0);
    }

    /**
     * 描述: 根据 key 停止任务
     *
     * @param taskKey
     * @return java.lang.Boolean
     * @author lv617
     * @date 2020/9/24 11:17
     */
    @Override
    public Boolean stop(String taskKey) {
        log.info(">>>>>> 进入停止任务 {}  >>>>>>", taskKey);
        //当前任务实例是否存在
        boolean taskStartFlag = scheduledFutureMap.containsKey(taskKey);
        log.info(">>>>>> 当前任务实例是否存在 {}", taskStartFlag);
        if (taskStartFlag) {
            //获取任务实例
            ScheduledFuture scheduledFuture = scheduledFutureMap.get(taskKey);
            //关闭实例
            boolean cancel = scheduledFuture.cancel(true);
            log.info("cancel:{}", cancel);
            //删除关闭的任务实例
            scheduledFutureMap.remove(taskKey);
            Scheduled scheduled = this.getByTaskKey(taskKey);
            if (!StringUtils.isEmpty(scheduled) && scheduled.getTaskStatus().equals(ScheduledStatus.DISABLE.getCode())){
                scheduled.setTaskStatus(ScheduledStatus.ENABLE.getCode());
                this.updateById(scheduled);
            }
        }
        log.info(">>>>>> 结束停止任务 {}  >>>>>>", taskKey);
        return taskStartFlag;
    }

    /**
     * 描述: 根据任务key 重启任务
     *
     * @param taskKey
     * @param scheduled
     */
    @Override
    public Boolean restart(String taskKey, Scheduled scheduled) {
        log.info(">>>>>> 进入重启任务 {}  >>>>>>", taskKey);
        //先停止
        this.stop(taskKey);
        if(scheduled == null){
            //查询配置
            scheduled = this.getByTaskKey(taskKey);
        }
        if(scheduled == null){
            return false;
        }
        //再启动
        return this.start(taskKey,scheduled);
    }

    /**
     * 初始化  ==> 启动所有正常状态的任务
     */
    @Override
    public void initAllTask() {
        if(!taskEnable){
            log.info("配置文件禁用了定时任务----");
            return;
        }
        EntityWrapper<Scheduled> wrapper = new EntityWrapper<>();
        wrapper.eq("task_status",ScheduledStatus.DISABLE.getCode());
        List<Scheduled> scheduleds = scheduledMapper.selectList(wrapper);
        log.info("初始化  ==> 启动所有正常状态的任务开始 ！size={}", scheduleds == null ? 0 : scheduleds.size());
        if (CollectionUtils.isEmpty(scheduleds)) {
            return;
        }
        for (Scheduled scheduled : scheduleds) {
            //任务 key
            String taskKey = scheduled.getTaskKey();
            //校验是否已经启动
            if (this.isStart(taskKey)) {
                // 重启任务
                this.restart(taskKey,scheduled);
            } else {
                // 启动任务
                this.doStartTask(scheduled);
            }
        }
        log.info("初始化  ==> 启动所有正常状态的任务结束 ！");
    }

    /**
     * 执行启动任务
     */
    private void doStartTask(Scheduled scheduled) {
        if (scheduled == null)
            return;
        //任务key
        String taskKey = scheduled.getTaskKey();
        //定时表达式
        String taskCron = scheduled.getTaskCron();
        //获取需要定时调度的接口
        ScheduledTaskJob scheduledTaskJob = (ScheduledTaskJob) SpringContextUtil.getBean(taskKey);
        log.info(">>>>>> 任务 [ {} ] ,cron={}", scheduled.getTaskName(), taskCron);
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(scheduledTaskJob, (TriggerContext triggerContext) -> new CronTrigger(taskCron).nextExecutionTime(triggerContext));
        //将启动的任务放入 map
        scheduledFutureMap.put(taskKey, scheduledFuture);
    }

    /**
     * 任务是否已经启动
     */
    private Boolean isStart(String taskKey) {
        //校验是否已经启动
        if (scheduledFutureMap.containsKey(taskKey)) {
            if (!scheduledFutureMap.get(taskKey).isCancelled()) {
                return true;
            }
        }
        return false;
    }

}

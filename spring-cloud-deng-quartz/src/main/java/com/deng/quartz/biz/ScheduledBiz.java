package com.deng.quartz.biz;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.deng.commons.config.result.ResultVOUtils;
import com.deng.commons.config.result.ResultVo;
import com.deng.commons.entitys.page.EntyPage;
import com.deng.commons.enums.ResultEnum;
import com.deng.commons.utils.MyBeanUtils;
import com.deng.quartz.entitys.Scheduled;
import com.deng.quartz.enums.ScheduledStatus;
import com.deng.quartz.form.ScheduledForm;
import com.deng.quartz.service.ScheduledTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther ZongCai
 * @Date 2021/7/16
 */
@Component
public class ScheduledBiz {

    @Resource
    private ScheduledTaskService scheduledtaskservice;

    /**
     * 列表查询所有定时任务(分页)
     *
     * @return
     */
    public ResultVo<PageInfo<List<Scheduled>>> queryScheduledTaskPage(String taskKey, EntyPage entyPage) {
        if (entyPage == null){
            entyPage = new EntyPage();
            entyPage.setCurrentPage(1);
        }
        entyPage.setPageSize(9999);
        PageHelper.startPage(entyPage.getCurrentPage(), entyPage.getPageSize());
        EntityWrapper<Scheduled> wrapper = new EntityWrapper<>();
        wrapper.like("task_key",taskKey);
        List<Scheduled> quartzTaskInformations = scheduledtaskservice.selectList(wrapper);
        PageInfo pageInfo = new PageInfo(quartzTaskInformations);
        return ResultVOUtils.success(pageInfo);
    }

    /**
     * 根据任务key 启动任务
     */
    public ResultVo start(String taskKey){
        Boolean flag = scheduledtaskservice.start(taskKey,null);
        if (flag){
            return ResultVOUtils.success("启动任务成功");
        }
        return ResultVOUtils.error(ResultEnum.RUN_NOW_FAIL,"启动任务失败");
    }

    /**
     * 根据任务key 停止任务
     */
    public ResultVo stop(String taskKey){
        Boolean flag = scheduledtaskservice.stop(taskKey);
        if (flag){
            return ResultVOUtils.success("停止任务成功");
        }
        return ResultVOUtils.error(ResultEnum.RUN_NOW_FAIL,"停止任务失败");
    }

    /**
     * 根据任务key 重启任务
     */
    public ResultVo restart(String taskKey, Scheduled scheduled){
        Boolean flag = scheduledtaskservice.restart(taskKey,scheduled);
        if (flag){
            return ResultVOUtils.success("重启任务成功");
        }
        return ResultVOUtils.error(ResultEnum.RUN_NOW_FAIL,"重启任务失败");
    }

    /**
     * 初始化  ==> 启动所有正常状态的任务
     */
    public ResultVo initAllTask(){
        scheduledtaskservice.initAllTask();
        return ResultVOUtils.success();
    }


    /**
     * 添加任务
     * @param scheduledForm
     * @return
     */
    public ResultVo addTask(ScheduledForm scheduledForm){
        Scheduled scheduled = new Scheduled();
        MyBeanUtils.copyProperties(scheduledForm,scheduled);

        EntityWrapper<Scheduled> wrapper = new EntityWrapper<>();
        wrapper.eq("task_key",scheduled.getTaskKey());
        int count = scheduledtaskservice.selectCount(wrapper);
        //判断是否重复任务编号
        if (count > 0) {
            return ResultVOUtils.error(ResultEnum.TASKNO_EXIST);
        }
        scheduled.setGmtCreatedOn(new Date());
        scheduled.setGmtUpdatedOn(new Date());
        boolean flag = scheduledtaskservice.insert(scheduled);
        if (flag) {
            return ResultVOUtils.success();
        }
        return ResultVOUtils.error(ResultEnum.FAIL,"任务添加失败");
    }

    /**
     * 通过任务编号 获取任务
     * @param taskKey
     * @return
     */
    public Scheduled getTaskByTaskKey(String taskKey){
        EntityWrapper<Scheduled> wrapper = new EntityWrapper<>();
        wrapper.eq("task_key",taskKey);
        return scheduledtaskservice.selectOne(wrapper);
    }

    /**
     * 立即运行一次定时任务
     *
     * @param taskKey
     * @return
     */
    public ResultVo runTaskRightNow(String taskKey){
        // 功能逻辑
        return ResultVOUtils.success();
    }

    /**
     * 批量删除任务
     *
     * @param ids
     * @return
     */
    public ResultVo deleteBatchByIds(List<Long> ids){
        EntityWrapper<Scheduled> wrapper = new EntityWrapper<>();
        wrapper.in("id",ids);
        List<Scheduled> scheduleds = scheduledtaskservice.selectList(wrapper);
        if (CollectionUtils.isEmpty(scheduleds)){
            ResultVOUtils.error(ResultEnum.DATA_NOT);
        }
        for (Scheduled scheduled: scheduleds){
            Boolean flag = scheduledtaskservice.stop(scheduled.getTaskKey());
            if (flag){
                scheduledtaskservice.deleteById(scheduled.getId());
            }
        }
        return ResultVOUtils.success();
    }

    /**
     * 编辑任务
     * @param scheduledForm
     * @return
     */
    public ResultVo editTask(ScheduledForm scheduledForm){
        // 获取原任务信息
        Scheduled scheduled = scheduledtaskservice.selectById(scheduledForm.getId());
        if (StringUtils.isEmpty(scheduled) || StringUtils.isEmpty(scheduledForm.getTaskKey())){
            return ResultVOUtils.error(ResultEnum.DATA_NOT);
        }
        if (!scheduledForm.getTaskKey().equals(scheduled.getTaskKey())){
            EntityWrapper<Scheduled> wrapper = new EntityWrapper<>();
            wrapper.eq("task_key",scheduledForm.getTaskKey());
            wrapper.ne("id",scheduled.getId());
            int count = scheduledtaskservice.selectCount(wrapper);
            //判断是否重复任务key值
            if (count > 0) {
                return ResultVOUtils.error(ResultEnum.TASKNO_EXIST);
            }
        }
        MyBeanUtils.copyProperties(scheduledForm,scheduled);
        scheduled.setTaskStatus(ScheduledStatus.ENABLE.getCode());
        scheduled.setGmtUpdatedOn(new Date());
        boolean flag = scheduledtaskservice.updateById(scheduled);
        scheduledtaskservice.stop(scheduled.getTaskKey());
        if (flag){
            return ResultVOUtils.success();
        }
        return ResultVOUtils.error(ResultEnum.FAIL,"任务编辑失败");
    }
}

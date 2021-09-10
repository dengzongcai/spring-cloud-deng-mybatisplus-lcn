package com.deng.quartz.controller;

import com.deng.commons.config.result.ResultVOUtils;
import com.deng.commons.config.result.ResultVo;
import com.deng.commons.entitys.page.EntyPage;
import com.deng.commons.enums.ResultEnum;
import com.deng.commons.forms.CommonIdsForm;
import com.deng.quartz.biz.ScheduledBiz;
import com.deng.quartz.entitys.Scheduled;
import com.deng.quartz.form.ScheduledForm;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 参考：https://blog.csdn.net/qq_14861089/article/details/108831101?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_baidulandingword-0&spm=1001.2101.3001.4242
 */
@Api(tags = "定时任务管理")
@RequestMapping("/scheduled")
@RestController
@Slf4j
public class ScheduledController {

    @Resource
    private ScheduledBiz scheduledbiz;

    @ApiOperation(value="定时任务列表", notes="定时任务列表")
    @PostMapping("/queryScheduledTaskPage" )
    public ResultVo<PageInfo<List<Scheduled>>> queryScheduledTaskPage(String taskKey, EntyPage entyPage) {
        try {
            return scheduledbiz.queryScheduledTaskPage(taskKey, entyPage);
        } catch (Exception e) {
            log.error("首页跳转发生异常exceptions-->" + e.toString());
            return ResultVOUtils.error(ResultEnum.FAIL);
        }
    }

    @ApiOperation(value="添加任务", notes="添加任务")
    @ResponseBody
    @PostMapping("/add/task")
    public ResultVo addTask(ScheduledForm scheduledForm) {
        return scheduledbiz.addTask(scheduledForm);
    }

    @ApiOperation(value="编辑任务", notes="编辑任务")
    @ResponseBody
    @PostMapping("/edit/task")
    public ResultVo editTask(ScheduledForm scheduledForm) {
        return scheduledbiz.editTask(scheduledForm);
    }

    /**
     * 启动 任务
     *
     * @param taskKey
     * @return
     */
    @ApiOperation(value="启动任务", notes="启动任务")
    @ResponseBody
    @PostMapping("/startJob")
    public ResultVo startJob(String taskKey) {
        if (StringUtils.isEmpty(taskKey)) {
            return ResultVOUtils.error(ResultEnum.PARAM_EMPTY);
        }
        try {
            return scheduledbiz.start(taskKey);
        } catch (Exception e) {
            log.error("/startJob exception={}", e);
        }
        return ResultVOUtils.error(ResultEnum.FAIL);
    }

    /**
     * 暂停 任务
     *
     * @param taskKey
     * @return
     */
    @ApiOperation(value="暂停任务", notes="暂停任务")
    @ResponseBody
    @PostMapping("/stopJob")
    public ResultVo stopJob(String taskKey) {
        if (StringUtils.isEmpty(taskKey)) {
            return ResultVOUtils.error(ResultEnum.PARAM_EMPTY);
        }
        try {
            return scheduledbiz.stop(taskKey);
        } catch (Exception e) {
            log.error("/stopJob exception={}", e);
        }
        return ResultVOUtils.error(ResultEnum.FAIL);
    }

    /**
     * 获取任务详情
     * @param taskKey
     * @return
     */
    @ApiOperation(value="获取任务详情", notes="获取任务详情")
    @PostMapping("/queryDetailByTaskKey")
    public ResultVo<Scheduled> queryDetailByTaskKey(String taskKey){
        if (StringUtils.isEmpty(taskKey)){
            return ResultVOUtils.error(ResultEnum.DATA_NOT);
        }
        return ResultVOUtils.success(scheduledbiz.getTaskByTaskKey(taskKey));
    }

    /**
     * 立即运行一次定时任务
     *
     * @param taskKey
     * @return
     */
    @ApiOperation(value="立即运行一次定时任务", notes="立即运行一次定时任务")
    @ResponseBody
    @PostMapping("/runtask/rightnow")
    public ResultVo runTaskRightNow(String taskKey) {
        try {
            if (StringUtils.isEmpty(taskKey)) {
                return ResultVOUtils.error(ResultEnum.PARAM_EMPTY);
            }
            return scheduledbiz.runTaskRightNow(taskKey);
        } catch (Exception e) {
            return ResultVOUtils.error(ResultEnum.FAIL);
        }
    }

    /**
     * 批量删除任务
     *
     * @param commonForm
     * @return
     */
    @ApiOperation(value="批量删除任务", notes="批量删除任务")
    @ResponseBody
    @PostMapping("/deleteBatchByIds")
    public ResultVo deleteBatchByIds(CommonIdsForm commonForm) {
        try {
            if (StringUtils.isEmpty(commonForm) || CollectionUtils.isEmpty(commonForm.getIds())) {
                return ResultVOUtils.error(ResultEnum.DATA_NOT);
            }
            return scheduledbiz.deleteBatchByIds(commonForm.getIds());
        } catch (Exception e) {
            return ResultVOUtils.error(ResultEnum.FAIL);
        }
    }
}

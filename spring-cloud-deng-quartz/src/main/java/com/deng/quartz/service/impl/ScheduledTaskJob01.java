package com.deng.quartz.service.impl;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.deng.commons.forms.synclog.BaseSyncLogForm;
import com.deng.commons.forms.useraccount.SaveOrUpdateUserAccountForm;
import com.deng.commons.utils.DateTimeUtils;
import com.deng.commons.utils.GenerateNoUtils;
import com.deng.quartz.service.feign.AccountFeignService;
import com.deng.quartz.service.ScheduledTaskJob;
import com.deng.quartz.service.feign.CurrentyFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 测试类01
 * @Auther ZongCai
 * @Date 2021/7/16
 */
@Service
@Slf4j
public class ScheduledTaskJob01 implements ScheduledTaskJob {
    @Autowired
    private AccountFeignService accountFeignService;
    @Autowired
    private CurrentyFeignService currentyFeignService;

    //事务消费者(即事务发起者)
    @LcnTransaction(propagation = DTXPropagation.REQUIRED)
    @Transactional(rollbackFor = Exception.class) //本地事务注解
    @Override
    public void run() {
        // TODO 要处理的业务逻辑
        log.info("定时任务开始创建用户账户 => ".concat(DateTimeUtils.getNowTime()));
        SaveOrUpdateUserAccountForm form = new SaveOrUpdateUserAccountForm();
        form.setAccountTypeId(1L);
        form.setUserId(1L);
        form.setRemark("测试：".concat(GenerateNoUtils.get()));
        accountFeignService.saveOrUpdateUserAccount(form);
        log.info("定时任务结束创建用户账户 => ".concat(DateTimeUtils.getNowTime()));

        log.info("定时任务开始创建系统日志 => ".concat(DateTimeUtils.getNowTime()));
        BaseSyncLogForm form1 = new BaseSyncLogForm();
        form1.setNewData(DateTimeUtils.getNowTime());
        form1.setOperationPerson("测试人员：".concat(GenerateNoUtils.get()));
        currentyFeignService.saveOrUpdateSyncLog(form1);
        log.info("定时任务结束创建系统日志 => ".concat(DateTimeUtils.getNowTime()));
        //int i = 1 / 0;
    }
}
package com.deng.currency.controller.feign;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.deng.commons.utils.DateTimeUtils;
import com.deng.currency.biz.BaseSyncLogBiz;
import com.deng.commons.forms.synclog.BaseSyncLogForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/synclogfeigncontroller")
@Slf4j
public class SyncLogFeignController {
    @Autowired
    private BaseSyncLogBiz basesynclogbiz;

    @LcnTransaction(propagation = DTXPropagation.SUPPORTS) //分布式事务注解(事物的参与方)
    @Transactional(rollbackFor = Exception.class) //本地事务注解
    @PostMapping("/saveOrUpdateSyncLog")
    public void saveOrUpdateSyncLog(@RequestBody BaseSyncLogForm form){
        log.info(DateTimeUtils.getNowTime().concat("===========开始执行添加/编辑日志"));
        basesynclogbiz.saveOrUpdate(form);
        log.info(DateTimeUtils.getNowTime().concat("===========开始执行添加/编辑日志"));
        // 故意异常
        int i = 1 / 0;
    }


}

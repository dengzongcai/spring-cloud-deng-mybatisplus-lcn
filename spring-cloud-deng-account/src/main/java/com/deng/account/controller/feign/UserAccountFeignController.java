package com.deng.account.controller.feign;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.deng.account.biz.UserAccountBiz;
import com.deng.commons.forms.useraccount.SaveOrUpdateUserAccountForm;
import com.deng.commons.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/useraccountfeign")
@Slf4j
public class UserAccountFeignController {
    @Autowired
    private UserAccountBiz useraccountbiz;

    @LcnTransaction(propagation = DTXPropagation.SUPPORTS) //分布式事务注解(事物的参与方)
    @Transactional(rollbackFor = Exception.class) //本地事务注解
    @PostMapping("/saveOrUpdateUserAccount")
    public void saveOrUpdateUserAccount(@RequestBody SaveOrUpdateUserAccountForm form){

        //DTXUserControls.rollbackGroup(TracingContext.tracing().groupId());

        log.info(DateTimeUtils.getNowTime().concat("===========开始执行添加/编辑用户账户"));
        useraccountbiz.saveOrUpdate(form);
        log.info(DateTimeUtils.getNowTime().concat("===========结束执行添加/编辑用户账户"));

        // 故意异常
        //int i = 1 / 0;
    }


}

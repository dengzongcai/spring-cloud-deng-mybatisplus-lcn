package com.deng.account.biz;

import com.deng.account.entitys.MbUserAccount;
import com.deng.account.service.MbUserAccountService;
import com.deng.commons.forms.useraccount.SaveOrUpdateUserAccountForm;
import com.deng.commons.utils.MyBeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;

/**
 * @Auther ZongCai
 * @Date 2021/9/7
 */
@Component
public class UserAccountBiz {
    @Resource
    private MbUserAccountService mbuseraccountservice;

    @Transactional
    public boolean saveOrUpdate(SaveOrUpdateUserAccountForm form){
        MbUserAccount mbuseraccount = new MbUserAccount();
        MyBeanUtils.copyProperties(form,mbuseraccount);
        if (StringUtils.isEmpty(mbuseraccount.getId())){
            // 添加
            return mbuseraccountservice.insert(mbuseraccount);
        }
        // 编辑
        return mbuseraccountservice.updateById(mbuseraccount);
    }
}

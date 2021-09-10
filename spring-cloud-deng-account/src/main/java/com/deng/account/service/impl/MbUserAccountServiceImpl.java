package com.deng.account.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.deng.account.entitys.MbUserAccount;
import com.deng.account.mapper.MbUserAccountMapper;
import com.deng.account.service.MbUserAccountService;
import org.springframework.stereotype.Service;

/**
 * @Auther ZongCai
 * @Date 2021/9/7
 */
@Service
public class MbUserAccountServiceImpl extends ServiceImpl<MbUserAccountMapper, MbUserAccount> implements MbUserAccountService {
}

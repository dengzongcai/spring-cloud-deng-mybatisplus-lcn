package com.deng.account.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.deng.account.entitys.AccountType;
import com.deng.account.mapper.AccountTypeMapper;
import com.deng.account.service.AccountTypeService;
import org.springframework.stereotype.Service;

/**
 * @Auther ZongCai
 * @Date 2021/9/7
 */
@Service
public class AccountTypeServiceImpl extends ServiceImpl<AccountTypeMapper, AccountType> implements AccountTypeService {
}

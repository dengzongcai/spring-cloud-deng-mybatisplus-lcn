package com.deng.currency.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.deng.currency.entitys.BaseSyncLog;
import com.deng.currency.mapper.BaseSyncLogMapper;
import com.deng.currency.service.IBaseSyncLogService;
import org.springframework.stereotype.Service;

@Service
public class BaseSyncLogServiceImpl extends ServiceImpl<BaseSyncLogMapper, BaseSyncLog> implements IBaseSyncLogService {

}

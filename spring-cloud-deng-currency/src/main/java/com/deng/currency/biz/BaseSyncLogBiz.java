package com.deng.currency.biz;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.deng.commons.config.result.ResultVOUtils;
import com.deng.commons.config.result.ResultVo;
import com.deng.commons.entitys.page.EntyPage;
import com.deng.commons.utils.MyBeanUtils;
import com.deng.currency.entitys.BaseSyncLog;
import com.deng.commons.forms.synclog.BaseSyncLogForm;
import com.deng.currency.service.IBaseSyncLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther ZongCai
 * @Date 2021/7/8
 */
@Component
public class BaseSyncLogBiz {
    @Resource
    private IBaseSyncLogService ibasesynclogservice;

    /**
     * 批量删除
     * @param ids 主键集（多条以英文逗号隔开）
     * @return
     */
    @Transactional
    public boolean deleteBatchIds(List<Long> ids){
        return ibasesynclogservice.deleteBatchIds(ids);
    }

    /**
     * 添加/编辑
     * @param form
     * @return
     */
    @Transactional
    public boolean saveOrUpdate(BaseSyncLogForm form){
        BaseSyncLog log = new BaseSyncLog();
        MyBeanUtils.copyProperties(form,log);
        if (StringUtils.isEmpty(log.getId())){
            // 添加
            return ibasesynclogservice.insert(log);
        }
        // 编辑
        return ibasesynclogservice.updateById(log);
    }

    /**
     * 条件查询
     * @return 查询结果集合
     */
    public List<BaseSyncLog> queryBaseSyncLogList(BaseSyncLogForm form){
        if (form == null){
            return ibasesynclogservice.selectList(null);
        }
        EntityWrapper<BaseSyncLog> wrapper = new EntityWrapper<>();
        wrapper.like("operation_person",form.getOperationPerson());
        return ibasesynclogservice.selectList(wrapper);
    }

    /**
     * 分页条件查询
     * @return 查询分页结果集合
     */
    public ResultVo<PageInfo<List<BaseSyncLog>>> queryBaseSyncLogPage(BaseSyncLogForm form, EntyPage entyPage){
        PageHelper.startPage(entyPage.getCurrentPage(), entyPage.getPageSize());
        List<BaseSyncLog> baseSyncLogs = null;
        if (form == null){
            baseSyncLogs = ibasesynclogservice.selectList(null);
        }else {
            EntityWrapper<BaseSyncLog> wrapper = new EntityWrapper<>();
            wrapper.like("operation_person",form.getOperationPerson());
            wrapper.like("new_data", form.getNewData());
            baseSyncLogs = ibasesynclogservice.selectList(wrapper);
        }
        PageInfo pageInfo = new PageInfo(baseSyncLogs);
        return ResultVOUtils.success(pageInfo);
    }

}

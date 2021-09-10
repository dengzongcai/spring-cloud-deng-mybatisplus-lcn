package com.deng.currency.biz;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.deng.commons.entitys.SysProperty;
import com.deng.commons.enums.StatusEnum;
import com.deng.commons.utils.MyBeanUtils;
import com.deng.currency.form.QuerySysPropertyValueForm;
import com.deng.currency.form.SaveOrUpdateSysPropertyForm;
import com.deng.currency.service.SysPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Auther ZongCai
 * @Date 2021/8/22
 */
@Component
public class SysPropertyBiz {
    @Resource
    private SysPropertyService syspropertyservice;

    /**
     * 获取所有有效配置信息
     * @return
     */
    public List<SysProperty> queryListSysProperty() {
        EntityWrapper<SysProperty> wrapper = new EntityWrapper<>();
        wrapper.eq("status", StatusEnum.ENABLE.code);
        List<SysProperty> sysProperties = syspropertyservice.selectList(wrapper);
        return sysProperties;
    }

    /**
     * 根据配置信息KEY 获取配置VALUE
     * @param form
     * @return
     */
    public String querySysPropertyByKey(QuerySysPropertyValueForm form) {
        if (Objects.isNull(form) || StringUtils.isEmpty(form.getKey())){
            return null;
        }
        EntityWrapper<SysProperty> wrapper = new EntityWrapper<>();
        wrapper.eq("key",form.getKey()).eq("status", StatusEnum.ENABLE.code);
        List<SysProperty> sysProperties = syspropertyservice.selectList(wrapper);
        if (CollectionUtils.isEmpty(sysProperties)){
            return null;
        }
        return sysProperties.get(0).getValues();
    }

    @Transactional
    public boolean saveOrUpdate(SaveOrUpdateSysPropertyForm form){
        SysProperty sysproperty = new SysProperty();
        MyBeanUtils.copyProperties(form,sysproperty);
        if (StringUtils.isEmpty(sysproperty.getId())){
            // 添加
            return syspropertyservice.insert(sysproperty);
        }
        // 编辑
        return syspropertyservice.updateById(sysproperty);
    }

    /**
     * 批量删除
     * @param ids 主键集（多条以英文逗号隔开）
     * @return
     */
    @Transactional
    public boolean deleteBatchIds(List<Long> ids){
        return syspropertyservice.deleteBatchIds(ids);
    }
}

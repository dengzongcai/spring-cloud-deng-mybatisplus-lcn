package com.deng.currency.biz;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.deng.commons.config.result.ResultVOUtils;
import com.deng.commons.config.result.ResultVo;
import com.deng.commons.entitys.SysArea;
import com.deng.commons.enums.ResultEnum;
import com.deng.currency.form.SysAreaForm;
import com.deng.currency.service.SysAreaService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther ZongCai
 * @Date 2021/7/13
 */
@Component
public class SysAreaBiz {
    @Resource
    private SysAreaService sysareaservice;

    /**选择所在地区
     * @param form
     * @return
     */
    public ResultVo<SysArea> queryAreaList(SysAreaForm form) {
        if (StringUtils.isEmpty(form)){
            return ResultVOUtils.error(ResultEnum.DATA_NOT);
        }
        if (StringUtils.isEmpty(form.getProvinceId())){
            // 查询多有的省列表
            EntityWrapper<SysArea> provinceWrapper = new EntityWrapper<>();
            provinceWrapper.eq("parent_id",0);
            List<SysArea> provinceList =  sysareaservice.selectList(provinceWrapper);
            return ResultVOUtils.success(provinceList);
        }
        // 查市
        if (StringUtils.isEmpty(form.getCityId())){
            // 查省下所有市
            EntityWrapper<SysArea> cityWrapper = new EntityWrapper<>();
            cityWrapper.eq("parent_id",form.getProvinceId());
            List<SysArea> provinceList =  sysareaservice.selectList(cityWrapper);
            return ResultVOUtils.success(provinceList);
        }
        // 查县/区
        if (StringUtils.isEmpty(form.getAreaId())){
            // 查市下所有县/区
            EntityWrapper<SysArea> areaWrapper = new EntityWrapper<>();
            areaWrapper.eq("parent_id",form.getCityId());
            List<SysArea> provinceList =  sysareaservice.selectList(areaWrapper);
            return ResultVOUtils.success(provinceList);
        }
        // 查确切县/区
        EntityWrapper<SysArea> areaWrapper = new EntityWrapper<>();
        areaWrapper.eq("id",form.getAreaId());
        List<SysArea> provinceList =  sysareaservice.selectList(areaWrapper);
        return ResultVOUtils.success(provinceList);
    }
}

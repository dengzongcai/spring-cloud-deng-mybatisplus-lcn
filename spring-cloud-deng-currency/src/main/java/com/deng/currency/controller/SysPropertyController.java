package com.deng.currency.controller;

import com.deng.commons.config.result.ResultVOUtils;
import com.deng.commons.config.result.ResultVo;
import com.deng.commons.enums.ResultEnum;
import com.deng.commons.forms.CommonIdsForm;
import com.deng.currency.biz.SysPropertyBiz;
import com.deng.currency.form.QuerySysPropertyValueForm;
import com.deng.currency.form.SaveOrUpdateSysPropertyForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Auther ZongCai
 * @Date 2021/8/22
 */
@RestController
@RequestMapping("/syspropertycontroller")
@Api(tags = "配置文件管理")
@Slf4j
public class SysPropertyController {
    @Resource
    private SysPropertyBiz syspropertybiz;

    /**
     * 批量删除
     * @return
     */
    @ApiOperation(value="批量删除", notes="批量删除")
    @PostMapping("/deleteBatchPropertys")
    public ResultVo deleteBatchPropertys(CommonIdsForm form) {
        if (Objects.isNull(form) || CollectionUtils.isEmpty(form.getIds())){
            return ResultVOUtils.error(ResultEnum.DATA_NOT);
        }
        boolean flag = syspropertybiz.deleteBatchIds(form.getIds());
        if (flag){
            return ResultVOUtils.success();
        }
        return ResultVOUtils.error(ResultEnum.ERR);
    }

    /**
     * 添加/编辑
     * @return
     */
    @ApiOperation(value="添加/编辑", notes="添加/编辑")
    @PostMapping("/saveOrUpdateProperty")
    public ResultVo saveOrUpdateProperty(SaveOrUpdateSysPropertyForm form) {
        boolean flag = syspropertybiz.saveOrUpdate(form);
        if (flag){
            return ResultVOUtils.success();
        }
        return ResultVOUtils.error(ResultEnum.ERR);
    }

    /**
     * 根据配置信息KEY 获取配置VALUE
     * @param form
     * @return
     */
    @ApiOperation(value="根据配置信息KEY 获取配置VALUE", notes="根据配置信息KEY 获取配置VALUE")
    @PostMapping("/querySysPropertyByKey")
    public ResultVo<String> querySysPropertyByKey(QuerySysPropertyValueForm form) {
        return ResultVOUtils.success(syspropertybiz.querySysPropertyByKey(form));
    }

}

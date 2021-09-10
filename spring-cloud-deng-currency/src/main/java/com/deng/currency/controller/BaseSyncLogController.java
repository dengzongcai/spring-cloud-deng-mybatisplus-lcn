package com.deng.currency.controller;

import com.deng.commons.config.result.ResultVOUtils;
import com.deng.commons.config.result.ResultVo;
import com.deng.commons.entitys.page.EntyPage;
import com.deng.commons.enums.ResultEnum;
import com.deng.commons.forms.CommonIdsForm;
import com.deng.currency.biz.BaseSyncLogBiz;
import com.deng.currency.entitys.BaseSyncLog;
import com.deng.commons.forms.synclog.BaseSyncLogForm;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/basesynclogcontroller")
@Api(tags = "日志管理")
public class BaseSyncLogController {

    @Resource
    private BaseSyncLogBiz basesynclogbiz;

    /**
     * 批量删除
     * @return
     */
    @ApiOperation(value="批量删除", notes="批量删除")
    @PostMapping("/deleteBatchLogs")
    public ResultVo deleteBatchLogs(CommonIdsForm form) {
        if (StringUtils.isEmpty(form) || CollectionUtils.isEmpty(form.getIds())){
            return ResultVOUtils.error(ResultEnum.DATA_NOT);
        }
        boolean flag = basesynclogbiz.deleteBatchIds(form.getIds());
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
    @PostMapping("/saveOrUpdateLog")
    public ResultVo saveOrUpdateProperty(BaseSyncLogForm form) {
        boolean flag = basesynclogbiz.saveOrUpdate(form);
        if (flag){
            return ResultVOUtils.success();
        }
        return ResultVOUtils.error(ResultEnum.ERR);
    }

    /**
     * 条件查询
     * @param form
     * @return
     */
    @ApiOperation(value="条件查询", notes="条件查询")
    @PostMapping("/queryBaseSyncLogList")
    public ResultVo<BaseSyncLog> queryBaseSyncLogList(@Valid BaseSyncLogForm form) {
        List<BaseSyncLog> list = basesynclogbiz.queryBaseSyncLogList(form);
        return ResultVOUtils.success(list);
    }

    /**
     * 分页条件查询
     * @param form
     * @return
     */
    @ApiOperation(value="分页条件查询", notes="分页条件查询")
    @PostMapping("/queryBaseSyncLogPage")
    public ResultVo<PageInfo<List<BaseSyncLog>>> queryBaseSyncLogPage(@Valid BaseSyncLogForm form, EntyPage entyPage) {
        return basesynclogbiz.queryBaseSyncLogPage(form, entyPage);
    }

}

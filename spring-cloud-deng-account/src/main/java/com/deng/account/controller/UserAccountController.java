package com.deng.account.controller;

import com.deng.account.biz.UserAccountBiz;
import com.deng.account.service.MbUserAccountService;
import com.deng.commons.config.result.ResultVOUtils;
import com.deng.commons.config.result.ResultVo;
import com.deng.commons.enums.ResultEnum;
import com.deng.commons.forms.CommonIdsForm;
import com.deng.commons.forms.useraccount.SaveOrUpdateUserAccountForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/useraccountcontroller")
@Api(tags = "用户账户管理")
@Slf4j
public class UserAccountController {

    @Resource
    private MbUserAccountService mbuseraccountservice;
    @Resource
    private UserAccountBiz useraccountbiz;

    /**
     * 批量删除用户账户
     * @return
     */
    @ApiOperation(value="批量删除用户账户", notes="批量删除用户账户")
    @PostMapping("/deleteBatchUserAccounts")
    public ResultVo deleteBatchUserAccounts(CommonIdsForm form) {
        if (StringUtils.isEmpty(form) || CollectionUtils.isEmpty(form.getIds())){
            return ResultVOUtils.error(ResultEnum.DATA_NOT);
        }
        boolean flag = mbuseraccountservice.deleteBatchIds(form.getIds());
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
    public ResultVo saveOrUpdateProperty(SaveOrUpdateUserAccountForm form) {
        boolean flag = useraccountbiz.saveOrUpdate(form);
        if (flag){
            return ResultVOUtils.success();
        }
        return ResultVOUtils.error(ResultEnum.ERR);
    }

}

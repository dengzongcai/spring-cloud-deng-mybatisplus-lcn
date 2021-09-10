package com.deng.currency.controller;

import com.deng.commons.config.result.ResultVo;
import com.deng.commons.entitys.SysArea;
import com.deng.currency.biz.SysAreaBiz;
import com.deng.currency.form.SysAreaForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Api(tags = "地区管理")
@RestController
@RequestMapping("/sysareacontroller")
public class SysAreaController {

	@Resource
	private SysAreaBiz sysareabiz;

	/**
	 * 选择所在地区
	 * @param form
	 * @return
	 */
	@ApiOperation(value="选择所在地区", notes="选择所在地区")
	@PostMapping("/queryAreaList")
	@ResponseBody
	public ResultVo<SysArea> queryAreaList(SysAreaForm form) {
		return sysareabiz.queryAreaList(form);
	}
}

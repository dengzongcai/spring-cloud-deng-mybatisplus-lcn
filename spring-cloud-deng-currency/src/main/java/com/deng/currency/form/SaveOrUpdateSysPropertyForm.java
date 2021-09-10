package com.deng.currency.form;

import com.deng.commons.forms.CommonIdForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther ZongCai
 * @Date 2021/8/22
 */
@Data
@ApiModel(value = "获取配置信息VALUE")
public class SaveOrUpdateSysPropertyForm extends CommonIdForm {
    @ApiModelProperty(value = "配置信息KEY")
    private String key;

    @ApiModelProperty(value = "配置信息VALUE")
    private String values;

    @ApiModelProperty(value = "备注")
    private String remarks;
}

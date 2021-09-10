package com.deng.commons.forms.useraccount;

import com.deng.commons.forms.CommonIdForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther ZongCai
 * @Date 2021/7/13
 */
@Data
public class SaveOrUpdateUserAccountForm extends CommonIdForm {
    @ApiModelProperty(value="实体用户id")
    private Long userId;

    @ApiModelProperty(value="账户类型id")
    private Long accountTypeId;

    @ApiModelProperty(value="备注")
    private String remark;
}

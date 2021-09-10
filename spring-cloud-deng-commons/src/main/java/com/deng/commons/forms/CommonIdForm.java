package com.deng.commons.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther ZongCai
 * @Date 2021/7/13
 */
@Data
public class CommonIdForm {
    @ApiModelProperty(value = "ID(编辑或删除时必传)")
    private Long id;
}

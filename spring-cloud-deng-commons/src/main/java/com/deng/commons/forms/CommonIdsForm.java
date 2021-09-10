package com.deng.commons.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther ZongCai
 * @Date 2021/7/13
 */
@Data
public class CommonIdsForm {
    @ApiModelProperty(value = "多条以英文逗号隔开")
    private List<Long> ids;
}

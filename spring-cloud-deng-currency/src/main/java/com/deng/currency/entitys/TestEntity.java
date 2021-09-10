package com.deng.currency.entitys;

import com.deng.commons.entitys.FullAuditedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
public class TestEntity extends FullAuditedEntity {
    @ApiModelProperty(value = "时间")
    private Date newData;
    @ApiModelProperty(value = "操作人")
    private String operationPerson;

    public static TestEntity getInstance;

    private TestEntity(){}

    public static TestEntity getGetInstance(){
        if (getInstance == null){
            getInstance = new TestEntity();
        }
        return getInstance;
    }

}

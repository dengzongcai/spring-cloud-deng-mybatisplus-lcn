package com.deng.commons.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Version;

import java.util.Date;

@Data
public class FullAuditedEntity extends Entity {

	@ApiModelProperty(value = "创建人")
	private String gmtCreatedBy;

	@ApiModelProperty(value = "创建日期")
	private Date gmtCreatedOn;

	@JsonIgnore
	@ApiModelProperty(value = "修改人")
	private String gmtUpdatedBy;
	
	@JsonIgnore
	@ApiModelProperty(value = "修改日期")
	private Date gmtUpdatedOn;
	
	@Version
	@ApiModelProperty(value = "锁版本")
	@JsonIgnore
	private Integer gmtVersion = 0;

	public  void resetFileds(){
		this.setId(null);
		this.gmtCreatedBy = null;
		this.gmtCreatedOn = null;
		this.gmtUpdatedBy = null;
		this.gmtUpdatedOn = null;
		this.gmtVersion = 0;
	}

}

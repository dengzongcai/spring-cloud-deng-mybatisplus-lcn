package com.deng.commons.entitys;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity implements Serializable {

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id == null || other.id == null) {
			return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	//http://mp.baomidou.com/#/question
	@JsonSerialize(using=ToStringSerializer.class)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

	@Override
	public String toString() {
		return "Entity [id=" + id + "]";
	}

	 
	@ApiModelProperty(value = "主键")
	// mybatis-plus在自动装载的时候会帮你把id设置为默认Long类型，而默认的idType就是ID_WORKER(UUID)，全局唯一
	@TableId(type = IdType.AUTO)
	private Long id;
}

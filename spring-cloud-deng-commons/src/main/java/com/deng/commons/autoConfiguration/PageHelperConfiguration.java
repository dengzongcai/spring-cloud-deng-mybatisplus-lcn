package com.deng.commons.autoConfiguration;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 
 * @ClassName: PageHelperConfiguration 
 * @Description: TODO
 * @author ZongCai
 * @date 2021/7/13
 */
@Configuration
public class PageHelperConfiguration {

	
	@Bean
	@ConditionalOnMissingBean
	public PageHelper interceptors(){
		//分页插件
		PageHelper pageHelper = new PageHelper();
		Properties props = new Properties();
		
		props.setProperty("reasonable", "true"); // 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
		props.setProperty("supportMethodsArguments", "true"); // 支持通过Mapper接口参数来传递分页参数
		props.setProperty("returnPageInfo", "check"); // always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
		
		pageHelper.setProperties(props);
		
		return pageHelper;
	}
	
	
	
}

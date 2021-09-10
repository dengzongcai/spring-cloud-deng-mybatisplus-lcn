package com.deng.commons.autoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 
 * @ClassName: WebConfiguration 
 * @Description: Spring MVC 配置
 * @author ZongCai
 * @date 2021/7/13
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
//	@Autowired
//	private RequestMappingHandlerAdapter handlerAdapter;
//
//
//	/**
//	 *
//	 * @Title: initEditableValidation
//	 * @Description: 数据转换
//	 * @param  参数说明
//	 * @return void 返回类型
//	 * @throws
//	 */
//	@PostConstruct
//	public void initEditableValidation() {
//		ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter
//				.getWebBindingInitializer();
//		if (initializer.getConversionService() != null) {
//			GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
//			genericConversionService.addConverter(new StringToDateConverter()); // Date类型转换
//			genericConversionService.addConverter(new StringToTimestampConverter()); // Timestamp类型转换
//		}
//
//	}
	@Bean
	public PageInterceptor pageInterceptor() {
		return new PageInterceptor();
	}
}

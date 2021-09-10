package com.deng.commons.config.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @ClassName: RestRuntimeException 
 * @Description: REST请求异常
 * @author ZongCai
 * @date 2021/7/13
 */
public class RestRuntimeException extends RuntimeException{
	
	static final long serialVersionUID = -7034897190745766939L;
	
	/**
	 * 状态码
	 */
	private HttpStatus httpStatus = null;
	
	
	public RestRuntimeException(String msg, HttpStatus httpStatus){
		super(msg);
		this.httpStatus = httpStatus;
	}

	
	/**
	 * 
	 * @Title: getHttpStatus 
	 * @Description: 获取异常状态码
	 * @param @return 参数说明 
	 * @return HttpStatus 返回类型 
	 * @throws
	 */
	public HttpStatus getHttpStatus(){
		return this.httpStatus;
	}
	
}

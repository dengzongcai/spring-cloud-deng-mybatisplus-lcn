package com.deng.currency.utils;

import com.deng.commons.common.qichacha.HttpHelper;
import com.deng.commons.common.qichacha.pojo.ECIExistVerify;
import com.deng.commons.common.qichacha.pojo.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.HttpHead;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;

import static java.lang.System.out;


/**
　* @Description: 企查查 工具类
　* @author ZongCai
　* @date 2022/8/4
　*/
@Component
public class QiChaChaUtil {
	// 查看我的秘钥 我的Key
	@Value("${qichacha.appkey}")
	private String appkey;
	@Value("${qichacha.seckey}")
	private String seckey;


	/**
	 * 企业工商数据查询 -> 企业工商详情
	 * search（公司名、注册号、社会统一信用代码或KeyNo）注：社会组织、香港企业仅支持通过企业名称和KeyNo查询
	 */
	public Root getBasicDetails(String search){
		String reqUrl = "http://api.qichacha.com/ECIV4/GetBasicDetailsByName";
		String paramStr = "keyword=".concat(search);
		try {
			HttpHead reqHeader = getHttpHead();
			String reqUri = reqUrl.concat("?key=").concat(appkey).concat("&").concat(paramStr);
			//发送get请求
			String tokenJson = HttpHelper.httpGet(reqUri, reqHeader.getAllHeaders());
			out.println(String.format("==========================>this is response:%s", tokenJson));
			String status = FormartJson(tokenJson, "Status");
			out.println(String.format("==========================>Status:%s", status));
			//json 转成 java对象

			Root root = com.alibaba.fastjson.JSONObject.parseObject(tokenJson, Root.class);
			out.println(root);
			return root;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}


	/**
	 * 企业是否存在
	 * search（公司名、注册号、社会统一信用代码或KeyNo）注：社会组织、香港企业仅支持通过企业名称和KeyNo查询
	 */
	public ECIExistVerify VerifyCom(String search){
		String reqUrl = "http://api.qichacha.com/ECIExistVerify/VerifyCom";
		String paramStr = "searchKey=".concat(search);
		try {
			HttpHead reqHeader = getHttpHead();
			String reqUri = reqUrl.concat("?key=").concat(appkey).concat("&").concat(paramStr);
			//发送get请求
			String tokenJson = HttpHelper.httpGet(reqUri, reqHeader.getAllHeaders());
			out.println(String.format("==========================>this is response:%s", tokenJson));
			String status = FormartJson(tokenJson, "Status");
			out.println(String.format("==========================>Status:%s", status));
			//json 转成 java对象

			ECIExistVerify root = com.alibaba.fastjson.JSONObject.parseObject(tokenJson, ECIExistVerify.class);
			out.println(root);
			return root;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private HttpHead getHttpHead() {
		//设置header
		HttpHead reqHeader = new HttpHead();
		String[] autherHeader = randomAuthentHeader();
		//token 是根据appkey 和 seckey 生成的
		reqHeader.setHeader("Token", autherHeader[0]);
		reqHeader.setHeader("Timespan", autherHeader[1]);
		return reqHeader;
	}



	// 获取返回码 Res Code
	static class HttpCodeRegex {
		private static final String ABNORMAL_REGIX = "(101)|(102)";
		private static final Pattern pattern = Pattern.compile(ABNORMAL_REGIX);
		protected static boolean isAbnornalRequest(final String status) {
			return pattern.matcher(status).matches();
		}
	}

	// 获取权限 Code
	protected final String[] randomAuthentHeader() {
		String timeSpan = String.valueOf(System.currentTimeMillis() / 1000);
		String[] authentHeaders = new String[] { DigestUtils.md5Hex(appkey.concat(timeSpan).concat(seckey)).toUpperCase(), timeSpan };
		return authentHeaders;
	}

	// 解析JSON
	public static String FormartJson(String jsonString, String key) throws JSONException {
		JSONObject jObject = new JSONObject(jsonString);
		return (String) jObject.get(key);
	}

	//优雅的输出 返回值
	protected static void PrettyPrintJson(String jsonString) throws JSONException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object obj = mapper.readValue(jsonString, Object.class);
			String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			out.println(indented);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

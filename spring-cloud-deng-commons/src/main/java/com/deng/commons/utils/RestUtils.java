package com.deng.commons.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName: RestUtils
 * @Description: Rest接口调用工具类
 * @author ZongCai
 * @date 2021/7/19
 */
public class RestUtils {
    /**
     * 接口调用(post请求) 数据处理
     *
     * @param url
     *            请求路径 例如：http://127.0.0.1:8080/test/test
     * @param param
     *            请求参数 例如：{ "userName":"Lily", "password":"123456" }
     * @return 响应数据 例如：{ "resultId":"1" "resultMsg":"操作成功" }
     */
    public  String insureResponsePost(String url, String param) {
        PrintWriter out = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = "";
        HttpURLConnection conn = null;
        StringBuffer strBuffer = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod( "POST");
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(300000);
            conn.setRequestProperty("Charset", "UTF-8");
            // 传输数据为json，如果为其他格式可以进行修改
            conn.setRequestProperty( "Content-Type", "application/json");
            conn.setRequestProperty( "Content-Encoding", "utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput( true);
            conn.setDoInput( true);
            conn.setUseCaches( false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            is = conn.getInputStream();
            br = new BufferedReader( new InputStreamReader(is));
            String line = null;
            while ((line=br.readLine())!= null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            System. out.println( "发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
                if (conn!= null) {
                    conn.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Http接口调用(get请求) 数据处理
     * @param url     请求地址  例如：http://127.0.0.1:8080/test/test?username=zhangsan$username=123456
     * @return
     */
    public  String insureResponseBlockGet(String url) {
        PrintWriter out = null;
        String result = "";
        HttpURLConnection conn = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer strBuffer = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(300000);
            // 传输数据为json，如果为其他格式可以进行修改
            conn.setRequestProperty("Content-Type", "application/json");
            is = conn.getInputStream();
            br = new BufferedReader( new InputStreamReader(is));
            String line = null;
            while ((line=br.readLine())!= null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            System.out.println( "发送 GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}

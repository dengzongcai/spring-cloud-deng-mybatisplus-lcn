package com.deng.commons.utils;

import com.baomidou.mybatisplus.toolkit.IdWorker;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: GenerateNoUtils
 * @Description: 根据类型生成编码
 * @author ZongCai
 * @date 2021/7/19
 */
public class GenerateNoUtils {

    /**
     * 生成唯一编码
     */
    public  static  String get(){
        return String.valueOf(IdWorker.getId());
    }

    /**
     * 生成规则编码
     *
     * @param type
     * @return
     */
    public static String createNo(String type) {
        //生成
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String s = sdf.format(new Date());
        String orderNo = type + "_" + sdf.format(new Date()) + (1 + (int) (Math.random() * 10000));
        return orderNo;
    }

    //创建md5加密数据
    public static String createNum(List<String> str) {
        String orderNo = "";
        for (int i = 0; i < str.size(); i++) {
            orderNo += str.get(i);
        }
        orderNo = getMD5String(orderNo);
        return orderNo;
    }

    /**
     * 根据多属性生成唯一编码
     *
     * @param consumeId
     * @param batchNumber
     * @param expirationDate
     * @param sterilizationBatchNumbe
     * @param sterilizationExpirationDate
     * @param consumePrice
     * @param supplierCode
     * @return
     */
    public static String getConsumeValue(String consumeId, String batchNumber, LocalDateTime expirationDate,  String sterilizationBatchNumbe, LocalDateTime sterilizationExpirationDate, BigDecimal consumePrice, String supplierCode) {
        String result = getMD5String(consumeId + batchNumber + expirationDate  + sterilizationBatchNumbe + sterilizationExpirationDate + consumePrice + supplierCode);
        return result;
    }

    /**
     * md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
            System.out.println("11"+null);
	 }


}

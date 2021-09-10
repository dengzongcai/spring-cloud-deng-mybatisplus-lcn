package com.deng.commons.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @ClassName: SecurityUtils
 * @Description: AES加/解密工具类
 * @author ZongCai
 * @date 2021/7/19
 */
public class SecurityUtils {
    //AES加密方式
    public static final String KEY_ALGORITHM = "AES";

    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 解密数据
     *
     * @param data
     *            待解密数据
     * @param key
     *            密钥
     * @return byte[] 解密后的数据
     * */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 欢迎密钥
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 加密数据
     *
     * @param data
     *            待加密数据
     * @param key
     *            密钥
     * @return byte[] 加密后的数据
     * */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     *
     * 生成密钥，java6只支持56位密钥，bouncycastle支持64位密钥
     *
     * @return byte[] 二进制密钥
     * */
    public static byte[] initkey() throws Exception {

        // 实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        // 初始化密钥生成器，AES要求密钥长度为128位、192位、256位
        kg.init(256);
        // 生成密钥
        SecretKey secretKey = kg.generateKey();
        // 获取二进制密钥编码形式
        return secretKey.getEncoded();
    }

    /**
     * 转换密钥
     *
     * @param key
     *            二进制密钥
     * @return Key 密钥
     * */
    public static Key toKey(byte[] key) throws Exception {
        // 实例化DES密钥
        // 生成密钥
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 初始化密钥
        byte[] key = Base64.decodeBase64("6M1133yyTothav0APxDAd+C4TFkDektkLgFcCjlQxaa=");// AESCoder.initkey();
        System.out.println("密钥：" + Base64.encodeBase64String(key));

        for (int i = 0; i < 1; i++) {
            String str = "我是一个快乐的人sadfasdf-" + i;
            System.out.println("原文：" + str);
            // 加密数据
            byte[] data = SecurityUtils.encrypt(str.getBytes(), key);
            System.out.println("加密后：" + Base64.encodeBase64String(data));
            // 解密数据
            data = SecurityUtils.decrypt(data, key);
            System.out.println("解密后：" + new String(data));
            System.out.println("========================");
        }
    }
}

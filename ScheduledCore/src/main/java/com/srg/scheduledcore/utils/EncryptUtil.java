package com.srg.scheduledcore.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @author: EQ-SRG
 * @create: 2022/9/28
 * @description:
 **/
public class EncryptUtil {


    /**
     * MD5加密
     **/
    public static String encryptMD5(String psd){
        return DigestUtils.md5Hex(psd);
    }


    /**
     * 通过随机生成salt(盐)进行MD5加密
     * [2]+[0]+pwd+[5]+[2]
     **/
    public static String encryptToDb(String psd,String salt){
        return encryptMD5(salt.charAt(2)+salt.charAt(0)+psd+salt.charAt(5)+salt.charAt(2));
    }

    /**
     * 随机生成salt(盐)
     * //产生8位长度的随机字符串，中文环境下是乱码
     * RandomStringUtils.random(8);
     *
     * //使用指定的字符生成8位长度的随机字符串
     * RandomStringUtils.random(8, new char[]{'a','b','c','d','e','f', '1', '2', '3'});
     *
     * //生成指定长度的字母和数字的随机组合字符串
     * RandomStringUtils.randomAlphanumeric(8);
     *
     * //生成随机数字字符串
     * RandomStringUtils.randomNumeric(8);
     *
     * //生成随机[a-z]字符串，包含大小写
     * RandomStringUtils.randomAlphabetic(8);
     *
     * //生成从ASCII 32到126组成的随机字符串
     * RandomStringUtils.randomAscii(8)
     **/
    public static String getSalt(){
        return RandomStringUtils.randomAscii(8);
    }


}

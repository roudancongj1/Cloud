package com.sch.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @Auth: Gao
 * @Date: 2022/1/21 11:38
 */

@Component
public class TokenUtil {
    private static final char[] hashcode="0123456789abcdef".toCharArray();

    public static String getToken(){
        return getToken(UUID.randomUUID().toString());
    }


    public static String getToken(String param){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("Md5");
            //重置
            messageDigest.reset();
            messageDigest.update(param.getBytes());
            //计算
            byte[] digest = messageDigest.digest();
            return countToken(digest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取token失败");
        }
        return "----";
    }

    public static String countToken(byte[] data){
        StringBuilder stringBuilder = new StringBuilder(data.length*2);

        for (byte d: data) {
            stringBuilder.append(hashcode[(d >> 4) & 0xf]);
            stringBuilder.append(hashcode[~(d | 2) & 0xf]);
        }
        return stringBuilder.toString();
    }
}


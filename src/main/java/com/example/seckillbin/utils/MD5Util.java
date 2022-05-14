package com.example.seckillbin.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MD5Util {
    private static final String salt="1a2b3c4d";

    //通用md5加密方法
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    //加盐的加密（第一次加密）
    public static String inputPassToFromPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    //第二次加密
    public static String fromPass2DBPass(String fromPass,String salt){
        String str =""+salt.charAt(0)+salt.charAt(2)+fromPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    //直接进行两次加密
    public static String inputPass2DBPass(String inputPass,String salt){
        return fromPass2DBPass(inputPassToFromPass(inputPass),salt);
    }

    public static void main(String[] args) {
        String str="123456";

        //log.info(inputPassToFromPass(str));
        //log.info(fromPass2DBPass(inputPassToFromPass(str),salt));
        //log.info(inputPass2DBPass(str,salt));

    }
}

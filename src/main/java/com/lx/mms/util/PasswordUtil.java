package com.lx.mms.util;

import java.util.Random;
import java.util.UUID;

/**
 * 生成随机密码
 */
public class PasswordUtil {

    public static String getRandomPassword(){
        String password = UUID.randomUUID().toString().replaceAll("-", "");
        int begin = new Random().nextInt(24);
        return password.substring(begin, begin + 7);
    }
}

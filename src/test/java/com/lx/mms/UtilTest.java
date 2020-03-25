package com.lx.mms;

import com.lx.mms.util.JsonMapper;
import com.lx.mms.util.LevelUtil;
import com.lx.mms.util.PasswordUtil;
import org.codehaus.jackson.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

public class UtilTest {

    private Dog dog = new Dog("tom", 18);

    @Test
    void jsonMapper(){

        String json = "name : aaa, age : 20";
        String string = JsonMapper.obj2String(dog);
        System.out.println(string);

    }

    @Test
    void levelUtil(){
        String level = LevelUtil.caculatateLevel("2", 1L);
        System.out.println(level);
    }

    @Test
    void password(){

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        System.out.println(uuid.toString().replaceAll("-", "").length());

        int num = new Random().nextInt(24);
        System.out.println(num);

        String randomPassword = PasswordUtil.getRandomPassword();
        System.out.println(randomPassword);
    }
}

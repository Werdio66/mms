package com.lx.mms;

import com.lx.mms.util.JsonMapper;
import com.lx.mms.util.LevelUtil;
import org.codehaus.jackson.type.TypeReference;
import org.junit.jupiter.api.Test;

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
}

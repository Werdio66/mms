package com.lx.mms;

import com.lx.mms.util.JsonMapper;
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
}

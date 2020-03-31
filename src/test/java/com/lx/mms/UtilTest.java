package com.lx.mms;

import com.lx.mms.util.JsonMapper;
import com.lx.mms.util.LevelUtil;
import com.lx.mms.util.PasswordUtil;
import org.codehaus.jackson.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    @Test
    void set(){
        List<Integer> list = Arrays.asList(1, 3, 5, 4, 2);
        Set<Integer> set = new HashSet<>(list);
        Set<Integer> set1 = new HashSet<>(list);


        set1.add(6);
//        set1.add(7);

        set.removeAll(set1);

        System.out.println(set.size());
    }

    @Test
    void list(){
        List<Long> list = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        List<Long> list1 = Arrays.asList(1L, 2L, 4L, 5L, 6L, 7L);

        list.sort((Comparator.comparing(o -> o)));
        list1.sort((Comparator.comparing(o -> o)));

        System.out.println(list.equals(list1));

    }
}

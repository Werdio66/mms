package com.lx.mms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lx.mms.util.JsonMapper;
import com.lx.mms.util.LevelUtil;
import com.lx.mms.util.PasswordUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UtilTest {

    private Dog dog = new Dog("tom", 18, LocalDateTime.now());

    @Test
    void jsonMapper(){

        String string = JsonMapper.obj2String(dog);
        System.out.println(string);

        Dog dog = JsonMapper.string2Obj(string, new TypeReference<Dog>() {});

        System.out.println(dog);


    }

    @Test
    void levelUtil(){
        String level = LevelUtil.caculatateLevel("2", 1L);
        System.out.println(level);
    }

    @Test
    void dateTimeFommart(){
        String time = "2021-11-12 12:12";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String format = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println(format);
        LocalDateTime localDateTime = LocalDateTime.parse(time, dateTimeFormatter);
        System.out.println(localDateTime);
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

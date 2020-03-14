package com.lx.mms;

import com.lx.mms.entity.SysUser;
import com.lx.mms.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
public class UserTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testSelect(){
        List<SysUser> users = sysUserMapper.queryAll();
        users.forEach(System.out::println);
        System.out.println("-------" + users.size() + "--------");
    }

    @Test
    void insert(){
        SysUser user = new SysUser();
        user.setUsername("11111");
        user.setTelephone("111111111");
        user.setMall("123@163.com");
        user.setDeptId(1L);
        user.setPassword("ffff");
        user.setRemark("fffffgggg");
        user.setOperator("ggggg");
        user.setOperatorIp("ffffgghhjj");
        System.out.println(sysUserMapper.insert(user));
    }
}

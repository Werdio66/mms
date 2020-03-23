package com.lx.mms.controller;

import com.lx.mms.TestEntity;
import com.lx.mms.common.ApplicationContextHelper;
import com.lx.mms.common.HttpInperceptor;
import com.lx.mms.entity.SysUser;
import com.lx.mms.mapper.SysUserMapper;
import com.lx.mms.util.BeanValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/hello.json")
    public String test(){

        throw new RuntimeException("run time ");
//        return "hello";
    }

    @RequestMapping("/mm")
    public String m(){

        System.out.println("========================================");
        return "a";
    }
    @GetMapping("/validate.json")
    public String validate(TestEntity t){
        log.info("参数：{}", t);
        /*Map<String, String> map = BeanValidation.validateObj(t);
        for (Map.Entry<String, String> entry : map.entrySet()){
            log.error("{} =======> {}", entry.getKey(), entry.getValue());
        }*/

        BeanValidation.check(t);
        return "ggg";
    }

    @Autowired
    private SysUserMapper sysUserMapper;

    @GetMapping("/select")
    public String select(){
        HttpInperceptor httpInperceptor = ApplicationContextHelper.popBean(HttpInperceptor.class);
        log.info("HttpInperceptor : {}", httpInperceptor);
        List<SysUser> users = sysUserMapper.queryAll();
        log.info("users : {}", users);
        return "ffff";
    }
}

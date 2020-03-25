package com.lx.mms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台页面
 */
@RequestMapping("/admin")
@Controller
@Slf4j
public class AdminController {

    @GetMapping("/index.page")
    public String index(){
        return "admin";
    }
}

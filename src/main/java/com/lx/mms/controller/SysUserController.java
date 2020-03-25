package com.lx.mms.controller;

import com.lx.mms.common.RespData;
import com.lx.mms.entity.SysUser;
import com.lx.mms.entity.param.UserParam;
import com.lx.mms.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Slf4j
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    @ResponseBody
    @GetMapping("/queryAll.json")
    public RespData queryAll(){
        log.info("查询用户信息：");
        List<SysUser> sysUsers = sysUserService.queryAll();

        return RespData.ok(sysUsers);
    }

    @ResponseBody
    @PostMapping("/save.json")
    public RespData save(UserParam userParam){
        log.info("添加用户：");
        log.info("用户信息：{}", userParam);
        int row = sysUserService.save(userParam);
        return RespData.ok(row);
    }
}
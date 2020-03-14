package com.lx.mms.controller;

import com.lx.mms.entity.SysUser;
import com.lx.mms.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysUser)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUser selectOne(Long id) {
        return this.sysUserService.queryById(id);
    }

}
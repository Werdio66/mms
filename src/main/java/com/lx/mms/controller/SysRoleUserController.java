package com.lx.mms.controller;

import com.lx.mms.entity.SysRoleUser;
import com.lx.mms.service.SysRoleUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysRoleUser)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@RestController
@RequestMapping("sysRoleUser")
public class SysRoleUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleUserService sysRoleUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysRoleUser selectOne(Long id) {
        return this.sysRoleUserService.queryById(id);
    }

}
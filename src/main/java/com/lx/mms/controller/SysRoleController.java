package com.lx.mms.controller;

import com.lx.mms.entity.SysRole;
import com.lx.mms.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysRole)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysRole selectOne(Long id) {
        return this.sysRoleService.queryById(id);
    }

}
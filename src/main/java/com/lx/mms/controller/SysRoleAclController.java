package com.lx.mms.controller;

import com.lx.mms.entity.SysRoleAcl;
import com.lx.mms.service.SysRoleAclService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysRoleAcl)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@RestController
@RequestMapping("sysRoleAcl")
public class SysRoleAclController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleAclService sysRoleAclService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysRoleAcl selectOne(Long id) {
        return this.sysRoleAclService.queryById(id);
    }

}
package com.lx.mms.controller;

import com.lx.mms.entity.SysAclModule;
import com.lx.mms.service.SysAclModuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysAclModule)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@RestController
@RequestMapping("sysAclModule")
public class SysAclModuleController {
    /**
     * 服务对象
     */
    @Resource
    private SysAclModuleService sysAclModuleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysAclModule selectOne(Long id) {
        return this.sysAclModuleService.queryById(id);
    }

}
package com.lx.mms.controller;

import com.lx.mms.entity.SysAcl;
import com.lx.mms.service.SysAclService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysAcl)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@RestController
@RequestMapping("sysAcl")
public class SysAclController {
    /**
     * 服务对象
     */
    @Resource
    private SysAclService sysAclService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysAcl selectOne(Long id) {
        return this.sysAclService.queryById(id);
    }

}
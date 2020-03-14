package com.lx.mms.controller;

import com.lx.mms.entity.SysDept;
import com.lx.mms.service.SysDeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysDept)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@RestController
@RequestMapping("sysDept")
public class SysDeptController {
    /**
     * 服务对象
     */
    @Resource
    private SysDeptService sysDeptService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysDept selectOne(Long id) {
        return this.sysDeptService.queryById(id);
    }

}
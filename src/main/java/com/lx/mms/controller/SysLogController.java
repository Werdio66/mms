package com.lx.mms.controller;

import com.lx.mms.entity.SysLog;
import com.lx.mms.service.SysLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysLog)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@RestController
@RequestMapping("sysLog")
public class SysLogController {
    /**
     * 服务对象
     */
    @Resource
    private SysLogService sysLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysLog selectOne(Long id) {
        return this.sysLogService.queryById(id);
    }

}
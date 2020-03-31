package com.lx.mms.controller;

import com.lx.mms.common.RespData;
import com.lx.mms.entity.SysRoleAcl;
import com.lx.mms.service.SysRoleAclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * (SysRoleAcl)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Slf4j
@RestController
@RequestMapping("/sys/roleAcl")
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

    @PostMapping("/save.json")
    public RespData save(String ids, Long roleId){
        log.info("为角色分配权限：");
        log.info("权限的 id = {}", ids);
        log.info("角色的 id = {}", roleId);
        int row = sysRoleAclService.saveRoleAclByRoleId(roleId, ids);
        return RespData.ok(row);
    }
}
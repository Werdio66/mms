package com.lx.mms.controller;

import com.lx.mms.common.RespData;
import com.lx.mms.dto.AclModuleLevelDto;
import com.lx.mms.entity.SysRole;
import com.lx.mms.entity.param.RoleParam;
import com.lx.mms.service.SysRoleService;
import com.lx.mms.service.impl.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * (SysRole)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Controller
@Slf4j
@RequestMapping("/sys/role")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    @Autowired
    private SysTreeService sysTreeService;

    @GetMapping("/index.page")
    public String index(){
        log.info("角色列表首页：");

        return "role";
    }

    @ResponseBody
    @GetMapping("/queryAll.json")
    public RespData queryAll(){
        log.info("查询所有的角色信息：");
        List<SysRole> sysRoles = sysRoleService.queryAll();
        log.info("角色信息为：{}", sysRoles);
        return RespData.ok(sysRoles);
    }

    @ResponseBody
    @PostMapping("/save.json")
    public RespData save(RoleParam roleParam){
        log.info("保存角色：");
        log.info("要保存的角色信息：{}", roleParam);

        int row = sysRoleService.save(roleParam);
        return RespData.ok(row);
    }

    @ResponseBody
    @PostMapping("/update.json")
    public RespData update(RoleParam roleParam){
        log.info("更新角色：");
        log.info("参数信息：{}", roleParam);

        int row = sysRoleService.update(roleParam);
        return RespData.ok(row);
    }

    @ResponseBody
    @GetMapping("/delete.json")
    public RespData delete(Long id){
        log.info("删除角色：");
        log.info("删除角色的 id = {}", id);

        sysRoleService.deleteById(id);

        return RespData.ok();
    }

    @ResponseBody
    @GetMapping("/roleTree.json")
    public RespData roleTree(Long roleId){
        log.info("角色权限树：");
        log.info("角色 id = {}", roleId);
        List<AclModuleLevelDto> aclModuleLevelDtos = sysTreeService.roleTree(roleId);
        log.info("权限树层级列表：{}", aclModuleLevelDtos);
        return RespData.ok(aclModuleLevelDtos);
    }

}
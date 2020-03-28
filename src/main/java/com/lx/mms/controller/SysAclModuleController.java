package com.lx.mms.controller;

import com.lx.mms.common.RespData;
import com.lx.mms.dto.AclModuleLevelDto;
import com.lx.mms.entity.param.AclModuleParam;
import com.lx.mms.service.SysAclModuleService;
import com.lx.mms.service.impl.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysAclModule)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Slf4j
@Controller
@RequestMapping("/sys/aclModule")
public class SysAclModuleController {
    /**
     * 服务对象
     */
    @Resource
    private SysAclModuleService sysAclModuleService;


    @Autowired
    private SysTreeService treeService;

    @GetMapping("/index.page")
    public String index(){
        log.info("权限列表页面：");
        return "acl";
    }

    @ResponseBody
    @PostMapping("/save.json")
    public RespData save(AclModuleParam aclModuleParam){
        log.info("保存权限：");
        log.info("要保存的权限信息：{}", aclModuleParam);

        int row = sysAclModuleService.save(aclModuleParam);
        return RespData.ok(row);
    }

    @ResponseBody
    @GetMapping("/tree.json")
    public RespData tree(){
        log.info("权限列表树：");
        List<AclModuleLevelDto> aclModuleLevelDtos = treeService.aclModelTree();
        log.info("查询到的权限树：{}", aclModuleLevelDtos);
        // 将权限树返回
        return RespData.ok(aclModuleLevelDtos);
    }

    @ResponseBody
    @PostMapping("/update.json")
    public RespData update(AclModuleParam aclModuleParam){
        log.info("更新权限：");
        log.info("参数信息：{}", aclModuleParam);

        int row = sysAclModuleService.update(aclModuleParam);
        return RespData.ok(row);
    }

    @ResponseBody
    @GetMapping("/delete.json")
    public RespData delete(Long id){
        log.info("删除权限：");
        log.info("删除权限的 id = {}", id);

        sysAclModuleService.deleteById(id);

        return RespData.ok();
    }
}
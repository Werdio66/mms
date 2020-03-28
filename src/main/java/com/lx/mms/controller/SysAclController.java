package com.lx.mms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.mms.common.RespData;
import com.lx.mms.entity.SysAcl;
import com.lx.mms.entity.param.AclParam;
import com.lx.mms.service.SysAclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysAcl)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Slf4j
@RestController
@RequestMapping("/sys/acl")
public class SysAclController {
    /**
     * 服务对象
     */
    @Resource
    private SysAclService sysAclService;

    @GetMapping("/queryAll.json")
    public RespData queryAll(){
        log.info("查询权限点信息：");
        List<SysAcl> sysAcls = sysAclService.queryAll();
        return RespData.ok(sysAcls);
    }
    
    @PostMapping("/save.json")
    public RespData save(AclParam aclParam){
        log.info("添加权限点：");
        log.info("权限点信息：{}", aclParam);
        int row = sysAclService.save(aclParam);
        return RespData.ok(row);
    }
    
     @PostMapping("/update.json")
    public RespData update(AclParam aclParam){
        log.info("修改权限点：");
        log.info("权限点信息：{}", aclParam);

        int row = sysAclService.update(aclParam);
        return RespData.ok(row);
    }
    
     @GetMapping("/delete.json")
    public RespData delete(Long id){
        log.info("删除权限点：");
        log.info("权限点 id = {}", id);

        sysAclService.deleteById(id);
        return RespData.ok();
    }
    
     @GetMapping("/queryByAclModuleId.json")
    public RespData queryByAclModuleId(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                  Long aclModuleId){

        log.info("查询指定权限模块的权限：");
        log.info("当前页：{}， 每页数量：{}， 权限模块 id ：{}", pageNum, pageSize, aclModuleId);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SysAcl> pageInfo = sysAclService.queryByAclModuleId(aclModuleId);
        log.info("查询到的权限点信息：{}", pageInfo);
        return RespData.ok(pageInfo);
    }
    
}
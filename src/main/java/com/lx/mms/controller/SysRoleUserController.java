package com.lx.mms.controller;

import com.google.common.collect.Lists;
import com.lx.mms.common.RespData;
import com.lx.mms.entity.SysRoleUser;
import com.lx.mms.entity.SysUser;
import com.lx.mms.service.SysRoleUserService;
import com.lx.mms.service.SysUserService;
import com.lx.mms.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (SysRoleUser)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Slf4j
@RestController
@RequestMapping("/sys/roleUser")
public class SysRoleUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleUserService sysRoleUserService;

    @PostMapping("/save.json")
    public RespData save(String ids, Long roleId){
        log.info("保存角色和用户的关系：");
        log.info("用户的 id = {}， 角色的 id = {}", ids, roleId);

        int row = sysRoleUserService.save(StringUtil.strToLongId(ids), roleId);
        return RespData.ok(row);
    }
}
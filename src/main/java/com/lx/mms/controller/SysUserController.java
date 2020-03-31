package com.lx.mms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.lx.mms.common.RespData;
import com.lx.mms.entity.SysUser;
import com.lx.mms.entity.param.UserParam;
import com.lx.mms.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (SysUser)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Slf4j
@Controller
@RequestMapping("/sys/user")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    @GetMapping("/noAuth.page")
    public String noAuth(){
        return "noAuth";
    }

    @ResponseBody
    @GetMapping("/queryAll.json")
    public RespData queryAll(){
        log.info("查询用户信息：");
        List<SysUser> sysUsers = sysUserService.queryAll();

        return RespData.ok(sysUsers);
    }

    @ResponseBody
    @PostMapping("/save.json")
    public RespData save(UserParam userParam){
        log.info("添加用户：");
        log.info("用户信息：{}", userParam);
        int row = sysUserService.save(userParam);
        return RespData.ok(row);
    }

    @ResponseBody
    @PostMapping("/update.json")
    public RespData update(UserParam userParam){
        log.info("修改用户：");
        log.info("用户信息：{}", userParam);

        int row = sysUserService.update(userParam);
        return RespData.ok(row);
    }

    @ResponseBody
    @GetMapping("/delete.json")
    public RespData delete(Long id){
        log.info("删除用户：");
        log.info("用户 id = {}", id);

        sysUserService.deleteById(id);
        return RespData.ok();
    }

    @ResponseBody
    @GetMapping("/queryByDeptId.json")
    public RespData queryByDeptId(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                  Long deptId){

        log.info("查询指定部门的用户：");
        log.info("当前页：{}， 每页数量：{}， 部门 id ：{}", pageNum, pageSize, deptId);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SysUser> users = sysUserService.queryByDeptId(deptId);
        log.info("查询到的用户信息：{}", users);
        return RespData.ok(users);
    }

    @ResponseBody
    @GetMapping("/userList.json")
    public RespData loadUserList(Long roleId){
        log.info("查询角色对应的用户信息：");
        log.info("角色 id = {}", roleId);
        // 查询指定角色的用户
        List<SysUser> selectedUserList = sysUserService.queryByRoleId(roleId);
        // 查询所有的用户
        List<SysUser> allUserList = sysUserService.queryAll();
        // 存放未指定角色的用户
        List<SysUser> unSelectUserList = Lists.newArrayList();

        Set<Long> selectIdSet = selectedUserList.stream().map(SysUser::getId).collect(Collectors.toSet());

        for (SysUser user : allUserList) {
            if (user.getStatus() == 1 && !selectIdSet.contains(user.getId())){
                unSelectUserList.add(user);
            }
        }

        Map<String, List<SysUser>> map = new HashMap<>();
        map.put("selected", selectedUserList);
        map.put("unselected", unSelectUserList);

        return RespData.ok(map);
    }
}
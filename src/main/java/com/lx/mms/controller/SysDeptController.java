package com.lx.mms.controller;

import com.lx.mms.common.RespData;
import com.lx.mms.dto.DeptLevelDto;
import com.lx.mms.entity.SysDept;
import com.lx.mms.entity.param.DeptParam;
import com.lx.mms.service.SysDeptService;
import com.lx.mms.service.impl.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysDept)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Slf4j
@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {
    /**
     * 服务对象
     */
    @Resource
    private SysDeptService sysDeptService;

    @Autowired
    private SysTreeService sysTreeService;

    @GetMapping("/index.page")
    public String index(){
        log.info("部门列表页面：");
        return "dept";
    }

    @ResponseBody
    @PostMapping("/save.json")
    public RespData save(DeptParam deptParam){
        log.info("保存部门：");
        log.info("要保存的部门信息：{}", deptParam);

        int row = sysDeptService.save(deptParam);
        return RespData.ok(row);
    }

    @ResponseBody
    @GetMapping("/tree.json")
    public RespData tree(){
        log.info("部门树：");
        List<DeptLevelDto> deptLevelDtos = sysTreeService.deptTree();
        log.info("查询到的部门树：{}", deptLevelDtos);
        // 将部门数返回
        return RespData.ok(deptLevelDtos);
    }

    @ResponseBody
    @PostMapping("/update.json")
    public RespData update(DeptParam deptParam){
        log.info("更新部门：");
        log.info("参数信息：{}", deptParam);

        int row = sysDeptService.update(deptParam);
        return RespData.ok(row);
    }

    @ResponseBody
    @GetMapping("/delete.json")
    public RespData delete(Long id){
        log.info("删除部门：");
        log.info("删除部门的 id = {}", id);

        sysDeptService.deleteById(id);

        return RespData.ok();
    }
}
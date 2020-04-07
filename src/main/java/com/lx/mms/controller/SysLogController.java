package com.lx.mms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.mms.common.RespData;
import com.lx.mms.entity.SysLog;
import com.lx.mms.entity.param.SearchLogParam;
import com.lx.mms.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysLog)表控制层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Slf4j
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
    /**
     * 服务对象
     */
    @Resource
    private SysLogService sysLogService;

    @GetMapping("/index.page")
    public String index(){
        log.info("权限更新记录页面：");

        return "log";
    }

    @ResponseBody
    @GetMapping("/recover.json")
    public RespData recover(Long id){
        log.info("还原操作：");
        log.info("日志记录的 id = {}", id);

        sysLogService.rerecover(id);
        return RespData.ok();
    }

    @ResponseBody
    @RequestMapping("/page.json")
    public RespData page(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                         SearchLogParam param) {
        log.info("加载日志记录：");
        log.info("当前页：{}，每页数量：{}", pageNum, pageSize);
        log.info("分页查询条件：{}", param);

        PageHelper.startPage(pageNum, pageSize);

        PageInfo<SysLog> pageInfo = sysLogService.queryPage(param);
        return RespData.ok(pageInfo);
    }
}
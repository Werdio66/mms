package com.lx.mms.service.impl;

import com.github.pagehelper.PageInfo;
import com.lx.mms.common.LogType;
import com.lx.mms.common.RequestHolder;
import com.lx.mms.dto.SearchLogDto;
import com.lx.mms.entity.*;
import com.lx.mms.entity.param.SearchLogParam;
import com.lx.mms.mapper.SysLogMapper;
import com.lx.mms.service.SysLogService;
import com.lx.mms.util.IpUtil;
import com.lx.mms.util.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * (SysLog)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysLog queryById(Long id) {
        return this.sysLogMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysLog> queryAllByLimit(int offset, int limit) {
        return this.sysLogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysLog> queryAll(){
        return this.sysLogMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysLog insert(SysLog sysLog) {
        this.sysLogMapper.insert(sysLog);
        return sysLog;
    }

    /**
     * 修改数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysLog update(SysLog sysLog) {
        this.sysLogMapper.update(sysLog);
        return this.queryById(sysLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysLogMapper.deleteById(id) > 0;
    }




    /**
     *  保存部门的日志操作
     * @param before        之前信息，保存部门时为null
     * @param after         之后信息，删除部门时为null
     */
    @Override
    public void saveDeptLog(SysDept before, SysDept after){
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_DEPT);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldvalue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewvalue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(LocalDateTime.now());
        sysLog.setStatus(1);
        sysLogMapper.insert(sysLog);
    }

     @Override
     public void saveUserLog(SysUser before, SysUser after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_USER);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldvalue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewvalue(after == null ? "" : JsonMapper.obj2String(after));
       sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(LocalDateTime.now());
        sysLog.setStatus(1);
        sysLogMapper.insert(sysLog);
    }

    @Override
    public void saveAclModuleLog(SysAclModule before, SysAclModule after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ACL_MODULE);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldvalue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewvalue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(LocalDateTime.now());
        sysLog.setStatus(1);
        sysLogMapper.insert(sysLog);
    }

    @Override
    public void saveAclLog(SysAcl before, SysAcl after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ACL_MODULE);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldvalue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewvalue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(LocalDateTime.now());
        sysLog.setStatus(1);
        sysLogMapper.insert(sysLog);
    }

    @Override
    public void saveRoleLog(SysRole before, SysRole after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ACL_MODULE);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldvalue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewvalue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(LocalDateTime.now());
        sysLog.setStatus(1);
        sysLogMapper.insert(sysLog);
    }

    @Override
    public void saveRoleAclLog(Long roleId, SysRoleAcl before, SysRoleAcl after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ACL_MODULE);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldvalue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewvalue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(LocalDateTime.now());
        sysLog.setStatus(1);
        sysLogMapper.insert(sysLog);
    }

    @Override
    public PageInfo<SysLog> queryPage(SearchLogParam param) {

        SearchLogDto dto = new SearchLogDto();
        dto.setType(param.getType());
        if (StringUtils.isNotBlank(param.getBeforeSeg())){
            dto.setBeforeSeg("%" + param.getBeforeSeg() + "%");
        }
        if (StringUtils.isNotBlank(param.getAfterSeg())){
            dto.setAfterSeg("%" + param.getAfterSeg() + "%");
        }
        if (StringUtils.isNotBlank(param.getOperator())){
            dto.setOperator("%" + param.getOperator() + "%");
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank(param.getFromTime())){
            String replace = param.getFromTime().replace('T', ' ');
            replace += ":00";
            dto.setFromTime(LocalDateTime.parse(replace, dateTimeFormatter));
        }
        if (StringUtils.isNotBlank(param.getToTime())){
            String replace = param.getToTime().replace('T', ' ');
            replace += ":00";
            dto.setToTime(LocalDateTime.parse(replace, dateTimeFormatter));
        }

        List<SysLog> logList = sysLogMapper.queryByCondition(dto);

        return new PageInfo<>(logList);
    }

    @Override
    public void saveRoleUserLog(Long roleId, SysRoleUser before, SysRoleUser after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ACL_MODULE);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldvalue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewvalue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(LocalDateTime.now());
        sysLog.setStatus(1);
        sysLogMapper.insert(sysLog);
    }
}
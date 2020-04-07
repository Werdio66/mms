package com.lx.mms.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.lx.mms.common.LogType;
import com.lx.mms.common.RequestHolder;
import com.lx.mms.dto.SearchLogDto;
import com.lx.mms.entity.*;
import com.lx.mms.entity.param.SearchLogParam;
import com.lx.mms.exception.ParamException;
import com.lx.mms.mapper.*;
import com.lx.mms.service.SysLogService;
import com.lx.mms.service.SysRoleAclService;
import com.lx.mms.service.SysRoleUserService;
import com.lx.mms.util.IpUtil;
import com.lx.mms.util.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysAclModuleMapper sysAclModuleMapper;
    @Resource
    private SysAclMapper sysAclMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleAclService sysRoleAclService;
    @Resource
    private SysRoleUserService sysRoleUserService;

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
        sysLog.setType(LogType.TYPE_ACL);
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
        sysLog.setType(LogType.TYPE_ROLE);
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
    public void saveRoleAclLog(Long roleId, List<Long> before, List<Long> after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ROLE_ACL);
        sysLog.setTargetId(roleId);
        sysLog.setOldvalue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewvalue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(LocalDateTime.now());
        sysLog.setStatus(1);
        sysLogMapper.insert(sysLog);
    }

    @Override
    public void saveRoleUserLog(Long roleId, List<Long> before, List<Long> after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ROLE_USER);
        sysLog.setTargetId(roleId);
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

    @Transactional
    @Override
    public void rerecover(Long id) {
        // 查询日志记录
        SysLog sysLog = sysLogMapper.queryById(id);
        Preconditions.checkNotNull(sysLog, "待还原的记录不存在");
        switch (sysLog.getType()) {
            case LogType.TYPE_DEPT:
                SysDept beforeDept = sysDeptMapper.queryById(sysLog.getTargetId());
                Preconditions.checkNotNull(beforeDept, "待还原的部门已经不存在了");
                if (StringUtils.isBlank(sysLog.getNewvalue()) || StringUtils.isBlank(sysLog.getOldvalue())) {
                    // TODO:可以去实现对应的 insert 和 delete 方法
                    throw new ParamException("删除和新增不还原");
                }
                // 将之前的 json 数据转换成对象
                SysDept afterDept = JsonMapper.string2Obj(sysLog.getOldvalue(), new TypeReference<SysDept>() {
                });
                afterDept.setOperator(RequestHolder.getCurrentUser().getUsername());
                afterDept.setOpetatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterDept.setOperatorTime(LocalDateTime.now());
                // 更新数据
                sysDeptMapper.update(afterDept);
                // 记录日志
                saveDeptLog(beforeDept, afterDept);
                break;
            case LogType.TYPE_USER:
                SysUser beforeUser = sysUserMapper.queryById(sysLog.getTargetId());
                Preconditions.checkNotNull(beforeUser, "待还原的用户已经不存在了");
                if (StringUtils.isBlank(sysLog.getNewvalue()) || StringUtils.isBlank(sysLog.getOldvalue())){
                    throw new ParamException("删除和新增不还原");
                }
                // 将之前的 json 数据转换成对象
                SysUser afterUser = JsonMapper.string2Obj(sysLog.getOldvalue(), new TypeReference<SysUser>() {
                });
                afterUser.setOperator(RequestHolder.getCurrentUser().getUsername());
                afterUser.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterUser.setOperatorTime(LocalDateTime.now());
                // 更新数据
                sysUserMapper.update(afterUser);
                // 记录日志
                saveUserLog(beforeUser, afterUser);
                break;
            case LogType.TYPE_ACL_MODULE:
                SysAclModule beforeAclModule = sysAclModuleMapper.queryById(sysLog.getTargetId());
                Preconditions.checkNotNull(beforeAclModule, "待还原的权限模块已经不存在了");
                if (StringUtils.isBlank(sysLog.getNewvalue()) || StringUtils.isBlank(sysLog.getOldvalue())){
                    throw new ParamException("删除和新增不还原");
                }
                // 将之前的 json 数据转换成对象
                SysAclModule afterAclModule = JsonMapper.string2Obj(sysLog.getOldvalue(), new TypeReference<SysAclModule>() {
                });
                afterAclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
                afterAclModule.setOpetatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterAclModule.setOperatorTime(LocalDateTime.now());
                // 更新数据
                sysAclModuleMapper.update(afterAclModule);
                // 记录日志
                saveAclModuleLog(beforeAclModule, afterAclModule);
                break;
            case LogType.TYPE_ACL:
                SysAcl beforeAcl = sysAclMapper.queryById(sysLog.getTargetId());
                Preconditions.checkNotNull(beforeAcl, "待还原的权限点已经不存在了");
                if (StringUtils.isBlank(sysLog.getNewvalue()) || StringUtils.isBlank(sysLog.getOldvalue())){
                    throw new ParamException("删除和新增不还原");
                }
                // 将之前的 json 数据转换成对象
                SysAcl afterAcl = JsonMapper.string2Obj(sysLog.getOldvalue(), new TypeReference<SysAcl>() {
                });
                afterAcl.setOperator(RequestHolder.getCurrentUser().getUsername());
                afterAcl.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterAcl.setOperatorTime(LocalDateTime.now());
                // 更新数据
                sysAclMapper.update(afterAcl);
                // 记录日志
                saveAclLog(beforeAcl, afterAcl);
                break;
            case LogType.TYPE_ROLE:
                SysRole beforeRole = sysRoleMapper.queryById(sysLog.getTargetId());
                Preconditions.checkNotNull(beforeRole, "待还原的角色已经不存在了");
                if (StringUtils.isBlank(sysLog.getNewvalue()) || StringUtils.isBlank(sysLog.getOldvalue())){
                    throw new ParamException("删除和新增不还原");
                }
                // 将之前的 json 数据转换成对象
                SysRole afterRole = JsonMapper.string2Obj(sysLog.getOldvalue(), new TypeReference<SysRole>() {
                });
                afterRole.setOperator(RequestHolder.getCurrentUser().getUsername());
                afterRole.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterRole.setOperatorTime(LocalDateTime.now());
                // 更新数据
                sysRoleMapper.update(afterRole);
                // 记录日志
                saveRoleLog(beforeRole, afterRole);
                break;
            case LogType.TYPE_ROLE_ACL:
                SysRole aclRole = sysRoleMapper.queryById(sysLog.getTargetId());
                Preconditions.checkNotNull(aclRole, "角色已经不存在了");
                String s = JsonMapper.string2Obj(sysLog.getOldvalue(), new TypeReference<String>() {
                });
                String trim = s.replace('[', ' ').replace(']', ' ').trim();
                sysRoleAclService.saveRoleAclByRoleId(sysLog.getTargetId(), trim);
                break;
            case LogType.TYPE_ROLE_USER:
                SysRole userRole = sysRoleMapper.queryById(sysLog.getTargetId());
                Preconditions.checkNotNull(userRole, "角色已经不存在了");
                sysRoleUserService.save(JsonMapper.string2Obj(sysLog.getOldvalue(), new TypeReference<List<Long>>() {
                }), sysLog.getTargetId());
                break;
            default:
                break;
        }
    }


}
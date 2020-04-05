package com.lx.mms.service.impl;

import com.github.pagehelper.PageInfo;
import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysAcl;
import com.lx.mms.entity.param.AclParam;
import com.lx.mms.entity.param.UserParam;
import com.lx.mms.exception.ParamException;
import com.lx.mms.mapper.SysAclMapper;
import com.lx.mms.service.SysAclService;
import com.lx.mms.service.SysLogService;
import com.lx.mms.util.BeanValidation;
import com.lx.mms.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * (SysAcl)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysAclService")
public class SysAclServiceImpl implements SysAclService {
    @Resource
    private SysAclMapper sysAclMapper;
    @Autowired
    private SysLogService sysLogService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysAcl queryById(Long id) {
        return this.sysAclMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysAcl> queryAllByLimit(int offset, int limit) {
        return this.sysAclMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysAcl> queryAll(){
        return this.sysAclMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysAcl 实例对象
     * @return 实例对象
     */
    @Override
    public SysAcl insert(SysAcl sysAcl) {
        this.sysAclMapper.insert(sysAcl);
        return sysAcl;
    }

    /**
     * 修改数据
     *
     * @param aclParam 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public int update(AclParam aclParam) {
        checkAcl(aclParam);
        SysAcl acl = buildAcl(aclParam);
        SysAcl before = sysAclMapper.queryById(aclParam.getId());
        acl.setId(aclParam.getId());
        int row = sysAclMapper.update(acl);
        if (row > 0){
            sysLogService.saveAclLog(before, acl);
        }
        return row;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    @Override
    public boolean deleteById(Long id) {
        SysAcl acl = sysAclMapper.queryById(id);
        int row = this.sysAclMapper.deleteById(id);
        if (row > 0) {
            sysLogService.saveAclLog(acl, null);
        }
        return row > 0;
    }

    @Transactional
    @Override
    public int save(AclParam aclParam) {
        checkAcl(aclParam);
        SysAcl acl = buildAcl(aclParam);
        int row = sysAclMapper.insert(acl);
        if (row > 0){
            sysLogService.saveAclLog(null, acl);
        }
        return row;
    }

    private void checkAcl(AclParam aclParam) {
        // 校验参数
        BeanValidation.check(aclParam);
        // 检查当前模块下是否存在相同名称的权限点
        checkExit(aclParam.getAclModuleId(), aclParam.getName(), aclParam.getId());
    }

    /**
     * 检查当前模块下是否存在相同名称的权限点
     *
     * @param aclModuleId 父模块 id
     * @param name        当前插入的名称
     * @param id          如果是修改的话，会要判断除了自己以外有没有相同的
     */
    private void checkExit(Long aclModuleId, String name, Long id) {

        SysAcl acl = sysAclMapper.checkExit(aclModuleId, name, id);

        if (acl != null){
            throw new ParamException("当前权限模块下已经存在 " + name + " 这个权限点");
        }
    }

    private SysAcl buildAcl(AclParam aclParam) {
        SysAcl acl = SysAcl.builder().name(aclParam.getName()).aclModuleId(aclParam.getAclModuleId())
                .url(aclParam.getUrl()).type(aclParam.getType()).seq(aclParam.getSeq()).status(aclParam.getStatus())
                .remark(aclParam.getRemark()).build();

        acl.setOperator(RequestHolder.getCurrentUser().getUsername());
        acl.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        acl.setCode("");
        acl.setOperatorTime(LocalDateTime.now());
        return acl;
    }

    @Override
    public PageInfo<SysAcl> queryByAclModuleId(Long aclModuleId) {

        List<SysAcl> acls = sysAclMapper.querybyAclModelId(aclModuleId);
        acls.sort(Comparator.comparingInt(SysAcl::getSeq));
        return new PageInfo<>(acls);
    }
}
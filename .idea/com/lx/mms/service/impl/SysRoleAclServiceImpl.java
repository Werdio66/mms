package com.lx.mms.service.impl;

import com.lx.mms.entity.SysRoleAcl;
import com.lx.mms.mapper.SysRoleAclMapper;
import com.lx.mms.service.SysRoleAclService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRoleAcl)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:51:42
 */
@Service("sysRoleAclService")
public class SysRoleAclServiceImpl implements SysRoleAclService {
    @Resource
    private SysRoleAclMapper sysRoleAclMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRoleAcl queryById(Long id) {
        return this.sysRoleAclMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRoleAcl> queryAllByLimit(int offset, int limit) {
        return this.sysRoleAclMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysRoleAcl> queryAll(){
        return this.sysRoleAclMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysRoleAcl 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleAcl insert(SysRoleAcl sysRoleAcl) {
        this.sysRoleAclMapper.insert(sysRoleAcl);
        return sysRoleAcl;
    }

    /**
     * 修改数据
     *
     * @param sysRoleAcl 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleAcl update(SysRoleAcl sysRoleAcl) {
        this.sysRoleAclMapper.update(sysRoleAcl);
        return this.queryById(sysRoleAcl.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysRoleAclMapper.deleteById(id) > 0;
    }
}
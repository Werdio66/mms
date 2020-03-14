package com.lx.mms.service.impl;

import com.lx.mms.entity.SysAcl;
import com.lx.mms.mapper.SysAclMapper;
import com.lx.mms.service.SysAclService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * @param sysAcl 实例对象
     * @return 实例对象
     */
    @Override
    public SysAcl update(SysAcl sysAcl) {
        this.sysAclMapper.update(sysAcl);
        return this.queryById(sysAcl.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysAclMapper.deleteById(id) > 0;
    }
}
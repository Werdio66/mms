package com.lx.mms.service.impl;

import com.lx.mms.entity.SysAclModule;
import com.lx.mms.mapper.SysAclModuleMapper;
import com.lx.mms.service.SysAclModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysAclModule)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysAclModuleService")
public class SysAclModuleServiceImpl implements SysAclModuleService {
    @Resource
    private SysAclModuleMapper sysAclModuleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysAclModule queryById(Long id) {
        return this.sysAclModuleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysAclModule> queryAllByLimit(int offset, int limit) {
        return this.sysAclModuleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysAclModule> queryAll(){
        return this.sysAclModuleMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysAclModule 实例对象
     * @return 实例对象
     */
    @Override
    public SysAclModule insert(SysAclModule sysAclModule) {
        this.sysAclModuleMapper.insert(sysAclModule);
        return sysAclModule;
    }

    /**
     * 修改数据
     *
     * @param sysAclModule 实例对象
     * @return 实例对象
     */
    @Override
    public SysAclModule update(SysAclModule sysAclModule) {
        this.sysAclModuleMapper.update(sysAclModule);
        return this.queryById(sysAclModule.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysAclModuleMapper.deleteById(id) > 0;
    }
}
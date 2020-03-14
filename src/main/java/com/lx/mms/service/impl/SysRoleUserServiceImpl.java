package com.lx.mms.service.impl;

import com.lx.mms.entity.SysRoleUser;
import com.lx.mms.mapper.SysRoleUserMapper;
import com.lx.mms.service.SysRoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRoleUser)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysRoleUserService")
public class SysRoleUserServiceImpl implements SysRoleUserService {
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRoleUser queryById(Long id) {
        return this.sysRoleUserMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRoleUser> queryAllByLimit(int offset, int limit) {
        return this.sysRoleUserMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysRoleUser> queryAll(){
        return this.sysRoleUserMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysRoleUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleUser insert(SysRoleUser sysRoleUser) {
        this.sysRoleUserMapper.insert(sysRoleUser);
        return sysRoleUser;
    }

    /**
     * 修改数据
     *
     * @param sysRoleUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleUser update(SysRoleUser sysRoleUser) {
        this.sysRoleUserMapper.update(sysRoleUser);
        return this.queryById(sysRoleUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysRoleUserMapper.deleteById(id) > 0;
    }
}
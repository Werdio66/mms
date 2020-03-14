package com.lx.mms.service;

import com.lx.mms.entity.SysRoleAcl;
import java.util.List;

/**
 * (SysRoleAcl)表服务接口
 *
 * @author Werdio丶
 * @since 2020-03-14 09:51:42
 */
public interface SysRoleAclService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRoleAcl queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRoleAcl> queryAllByLimit(int offset, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<SysRoleAcl> queryAll();
    
    /**
     * 新增数据
     *
     * @param sysRoleAcl 实例对象
     * @return 实例对象
     */
    SysRoleAcl insert(SysRoleAcl sysRoleAcl);

    /**
     * 修改数据
     *
     * @param sysRoleAcl 实例对象
     * @return 实例对象
     */
    SysRoleAcl update(SysRoleAcl sysRoleAcl);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
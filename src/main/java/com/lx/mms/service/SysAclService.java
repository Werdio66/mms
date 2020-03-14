package com.lx.mms.service;

import com.lx.mms.entity.SysAcl;
import java.util.List;

/**
 * (SysAcl)表服务接口
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
public interface SysAclService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysAcl queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysAcl> queryAllByLimit(int offset, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<SysAcl> queryAll();
    
    /**
     * 新增数据
     *
     * @param sysAcl 实例对象
     * @return 实例对象
     */
    SysAcl insert(SysAcl sysAcl);

    /**
     * 修改数据
     *
     * @param sysAcl 实例对象
     * @return 实例对象
     */
    SysAcl update(SysAcl sysAcl);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
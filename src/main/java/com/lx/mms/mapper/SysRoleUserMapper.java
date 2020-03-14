package com.lx.mms.mapper;

import com.lx.mms.entity.SysRoleUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysRoleUser)表数据库访问层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
public interface SysRoleUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRoleUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRoleUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<SysRoleUser> queryAll();

    /**
     * 新增数据
     *
     * @param sysRoleUser 实例对象
     * @return 影响行数
     */
    int insert(SysRoleUser sysRoleUser);

    /**
     * 修改数据
     *
     * @param sysRoleUser 实例对象
     * @return 影响行数
     */
    int update(SysRoleUser sysRoleUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
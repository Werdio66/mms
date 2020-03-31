package com.lx.mms.mapper;

import com.lx.mms.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
public interface SysUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<SysUser> queryAll();

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    SysUser findByKeyword(String username);

    int countByMail(@Param("mail") String mail, @Param("id") Long id);

    int countByTelephone(@Param("telephone") String telephone, @Param("id") Long id);

    List<SysUser> queryByDeptId(Long deptId);

    List<SysUser> queryByRoleId(Long roleId);
}
package com.lx.mms.mapper;

import com.lx.mms.entity.SysLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysLog)表数据库访问层
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
public interface SysLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysLog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<SysLog> queryAll();

    /**
     * 新增数据
     *
     * @param sysLog 实例对象
     * @return 影响行数
     */
    int insert(SysLog sysLog);

    /**
     * 修改数据
     *
     * @param sysLog 实例对象
     * @return 影响行数
     */
    int update(SysLog sysLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
package com.lx.mms.service.impl;

import com.lx.mms.entity.SysDept;
import com.lx.mms.mapper.SysDeptMapper;
import com.lx.mms.service.SysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysDept)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Resource
    private SysDeptMapper sysDeptMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysDept queryById(Long id) {
        return this.sysDeptMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysDept> queryAllByLimit(int offset, int limit) {
        return this.sysDeptMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysDept> queryAll(){
        return this.sysDeptMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @Override
    public SysDept insert(SysDept sysDept) {
        this.sysDeptMapper.insert(sysDept);
        return sysDept;
    }

    /**
     * 修改数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @Override
    public SysDept update(SysDept sysDept) {
        this.sysDeptMapper.update(sysDept);
        return this.queryById(sysDept.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysDeptMapper.deleteById(id) > 0;
    }
}
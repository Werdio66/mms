package com.lx.mms.service.impl;

import com.lx.mms.entity.SysLog;
import com.lx.mms.mapper.SysLogMapper;
import com.lx.mms.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysLog)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysLog queryById(Long id) {
        return this.sysLogMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysLog> queryAllByLimit(int offset, int limit) {
        return this.sysLogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysLog> queryAll(){
        return this.sysLogMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysLog insert(SysLog sysLog) {
        this.sysLogMapper.insert(sysLog);
        return sysLog;
    }

    /**
     * 修改数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysLog update(SysLog sysLog) {
        this.sysLogMapper.update(sysLog);
        return this.queryById(sysLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysLogMapper.deleteById(id) > 0;
    }
}
package com.lx.mms.service.impl;

import com.google.common.base.Preconditions;
import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysDept;
import com.lx.mms.entity.param.DeptParam;
import com.lx.mms.exception.ParamException;
import com.lx.mms.mapper.SysDeptMapper;
import com.lx.mms.service.SysDeptService;
import com.lx.mms.service.SysLogService;
import com.lx.mms.util.BeanValidation;
import com.lx.mms.util.IpUtil;
import com.lx.mms.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Autowired
    private SysLogService sysLogService;

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
     * @param deptParam 更新后的参数
     * @return 实例对象
     */
    @Transactional
    @Override
    public int update(DeptParam deptParam) {
        // 校验参数
        checkDept(deptParam);
        // 获取修改之前的对象
        SysDept before = sysDeptMapper.queryById(deptParam.getId());
        // 通过构建者模式创建对象
        SysDept dept = buildDept(deptParam);
        dept.setId(deptParam.getId());
        int row = sysDeptMapper.update(dept);
        // 记录日志
        if (row > 0){
            sysLogService.saveDeptLog(before, dept);
        }
        return row;
    }

    @Transactional
    @Override
    public int save(DeptParam deptParam) {
        checkDept(deptParam);
        // 通过构建者模式创建对象
        SysDept dept = buildDept(deptParam);
        int row = sysDeptMapper.insert(dept);
        if (row > 0){
            // 记录日志
            sysLogService.saveDeptLog(null, dept);
        }
        return row;
    }

    // 校验参数
    private void checkDept(DeptParam deptParam){
        // 校验参数
        BeanValidation.check(deptParam);
        // 检查部门是否已经存在
        if (checkExist(deptParam.getParentId(), deptParam.getName(), deptParam.getId())){
            throw new ParamException("同一层级下存在相同的部门");
        }
    }

    // 根据传入的参数构建 dept 对象
    private SysDept buildDept(DeptParam deptParam){

        // 通过构建者模式创建对象
        SysDept dept = SysDept.builder().name(deptParam.getName()).remark(deptParam.getRemark())
                .parentId(deptParam.getParentId()).seq(deptParam.getSeq())
                .build();

        // 设置层级
        dept.setLevel(LevelUtil.caculatateLevel(getLevel(deptParam.getParentId()), deptParam.getParentId()));
        //
        dept.setOperator(RequestHolder.getCurrentUser().getUsername());
        dept.setOpetatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        dept.setOperatorTime(LocalDateTime.now());

        return dept;
    }

    /**
     *  检查要插入的部门是否已经存在
     * @param parentId
     * @param deptName
     * @param deptId
     * @return
     */
    private boolean checkExist(Long parentId, String deptName, Long deptId) {
        SysDept dept = sysDeptMapper.checkExit(parentId, deptName, deptId);
        return dept != null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    @Override
    public boolean deleteById(Long id) {
        SysDept before = sysDeptMapper.queryById(id);
        int row = sysDeptMapper.deleteById(id);
        // 记录日志
        if (row > 0){
            sysLogService.saveDeptLog(before, null);
        }
        return row > 0;
    }

    // 获取部门的层级
    private String getLevel(Long deptId){

        SysDept dept = sysDeptMapper.queryById(deptId);

        if (dept == null){
            return null;
        }

        return dept.getLevel();
    }
}
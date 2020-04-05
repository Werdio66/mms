package com.lx.mms.service.impl;

import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysRole;
import com.lx.mms.entity.param.RoleParam;
import com.lx.mms.exception.ParamException;
import com.lx.mms.mapper.SysRoleMapper;
import com.lx.mms.service.SysLogService;
import com.lx.mms.service.SysRoleService;
import com.lx.mms.util.BeanValidation;
import com.lx.mms.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (SysRole)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysLogService sysLogService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRole queryById(Long id) {
        return this.sysRoleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRole> queryAllByLimit(int offset, int limit) {
        return this.sysRoleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysRole> queryAll(){
        return this.sysRoleMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole insert(SysRole sysRole) {
        this.sysRoleMapper.insert(sysRole);
        return sysRole;
    }

    /**
     * 修改数据
     *
     * @param roleParam 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public int update(RoleParam roleParam) {
        checkRole(roleParam);

        SysRole role = buildRole(roleParam);
        SysRole before = sysRoleMapper.queryById(roleParam.getId());
        role.setId(roleParam.getId());
        int row = sysRoleMapper.update(role);
        if (row > 0){
            sysLogService.saveRoleLog(before, role);
        }
        return row;
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
        SysRole role = sysRoleMapper.queryById(id);
        int row = this.sysRoleMapper.deleteById(id);
        if (row > 0){
            sysLogService.saveRoleLog(role, null);
        }
        return row > 0;
    }

    @Transactional
    @Override
    public int save(RoleParam roleParam) {
        checkRole(roleParam);
        SysRole role = buildRole(roleParam);
        int row = sysRoleMapper.insert(role);
        if (row > 0){
            sysLogService.saveRoleLog(null, role);
        }
        return row;
    }

    private void checkRole(RoleParam roleParam) {
        // 校验参数
        BeanValidation.check(roleParam);
        // 查看角色是否已经存在
        checkExit(roleParam.getId(), roleParam.getName());
    }

    private void checkExit(Long id, String name) {
        SysRole role = sysRoleMapper.checkExit(name, id);

        if (role != null){
            throw new ParamException("角色已经存在");
        }
    }

    private SysRole buildRole(RoleParam roleParam) {

        SysRole role = SysRole.builder().name(roleParam.getName()).remark(roleParam.getRemark())
                .status(roleParam.getStatus()).type(roleParam.getType()).build();

        role.setOperator(RequestHolder.getCurrentUser().getUsername());
        role.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        role.setOperatorTime(LocalDateTime.now());
        return role;
    }
}
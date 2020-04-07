package com.lx.mms.service.impl;

import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysRoleAcl;
import com.lx.mms.mapper.SysRoleAclMapper;
import com.lx.mms.service.SysLogService;
import com.lx.mms.service.SysRoleAclService;
import com.lx.mms.util.IpUtil;
import com.lx.mms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (SysRoleAcl)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysRoleAclService")
public class SysRoleAclServiceImpl implements SysRoleAclService {
    @Resource
    private SysRoleAclMapper sysRoleAclMapper;

    @Autowired
    private SysLogService sysLogService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRoleAcl queryById(Long id) {
        return this.sysRoleAclMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRoleAcl> queryAllByLimit(int offset, int limit) {
        return this.sysRoleAclMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysRoleAcl> queryAll(){
        return this.sysRoleAclMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysRoleAcl 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleAcl insert(SysRoleAcl sysRoleAcl) {
        this.sysRoleAclMapper.insert(sysRoleAcl);
        return sysRoleAcl;
    }

    /**
     * 修改数据
     *
     * @param sysRoleAcl 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleAcl update(SysRoleAcl sysRoleAcl) {
        this.sysRoleAclMapper.update(sysRoleAcl);
        return this.queryById(sysRoleAcl.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysRoleAclMapper.deleteById(id) > 0;
    }

    @Transactional
    @Override
    public int saveRoleAclByRoleId(Long roleId, String aclIds) {
        // 获取之前指定角色的权限 id 列表
        List<Long> aclIdList = sysRoleAclMapper.getAclIdListByRoleId(roleId);
        // 删除之前分配的权限
        if (sysRoleAclMapper.countByRoleId(roleId) > 0){
            sysRoleAclMapper.deleteAclByRoleId(roleId);
        }

        if (StringUtils.isEmpty(aclIds)){
            return 0;
        }
        String[] split = aclIds.split(",");
        Long[] ids = new Long[split.length];

        int index = 0;
        for (String id : split) {
            ids[index++] = Long.parseLong(id);
        }

        int row = saveRoleAcl(roleId, ids);
        // 保存日志记录
        sysLogService.saveRoleAclLog(roleId, aclIdList, StringUtil.strToLongId(aclIds));
        return row;
    }

    private int saveRoleAcl(Long roleId, Long[] ids) {

        int row = 0;
        for (Long aclId : ids) {
            SysRoleAcl roleAcl = SysRoleAcl.builder().roleId(roleId).aclId(aclId).build();

            roleAcl.setOperator(RequestHolder.getCurrentUser().getUsername());
            roleAcl.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
            roleAcl.setOperatorTime(LocalDateTime.now());

            row += sysRoleAclMapper.insert(roleAcl);
        }

        return row;
    }
}
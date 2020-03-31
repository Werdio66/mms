package com.lx.mms.service.impl;

import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysRoleUser;
import com.lx.mms.entity.SysUser;
import com.lx.mms.mapper.SysRoleUserMapper;
import com.lx.mms.service.SysRoleUserService;
import com.lx.mms.util.IpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * (SysRoleUser)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysRoleUserService")
public class SysRoleUserServiceImpl implements SysRoleUserService {
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRoleUser queryById(Long id) {
        return this.sysRoleUserMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRoleUser> queryAllByLimit(int offset, int limit) {
        return this.sysRoleUserMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysRoleUser> queryAll(){
        return this.sysRoleUserMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysRoleUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleUser insert(SysRoleUser sysRoleUser) {
        this.sysRoleUserMapper.insert(sysRoleUser);
        return sysRoleUser;
    }

    /**
     * 修改数据
     *
     * @param sysRoleUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleUser update(SysRoleUser sysRoleUser) {
        this.sysRoleUserMapper.update(sysRoleUser);
        return this.queryById(sysRoleUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysRoleUserMapper.deleteById(id) > 0;
    }

    @Transactional
    @Override
    public int save(List<Long> userIds, Long roleId) {
        if (CollectionUtils.isEmpty(userIds)){
            sysRoleUserMapper.deleteByRoleId(roleId);
            return 0;
        }
        // 保存之前的用户 id
        List<Long> userIdList = sysRoleUserMapper.queryUserIdByRoleId(roleId);

        userIdList.sort(Comparator.comparing(o -> o));
        userIds.sort(Comparator.comparing(o -> o));

        // 判断数据是否修改
        if (userIdList.equals(userIds)){
            return 0;
        }
        // 删除之前的数据
        sysRoleUserMapper.deleteByRoleId(roleId);

        int row = 0;
        for (Long userId : userIds) {
            SysRoleUser roleUser = SysRoleUser.builder().roleId(roleId).userId(userId).operator(RequestHolder.getCurrentUser().getUsername())
                    .operatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest())).operatorTime(LocalDateTime.now()).build();

            row += sysRoleUserMapper.insert(roleUser);
        }
        return row;
    }
}
package com.lx.mms.service.impl;

import com.github.pagehelper.PageInfo;
import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysUser;
import com.lx.mms.entity.param.UserParam;
import com.lx.mms.exception.BaseException;
import com.lx.mms.mapper.SysUserMapper;
import com.lx.mms.service.SysLogService;
import com.lx.mms.service.SysUserService;
import com.lx.mms.util.BeanValidation;
import com.lx.mms.util.IpUtil;
import com.lx.mms.util.MD5Util;
import com.lx.mms.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysLogService sysLogService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Long id) {
        return this.sysUserMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAll(){
        return this.sysUserMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserMapper.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param userParam 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public int update(UserParam userParam) {
         // 校验参数
        BeanValidation.check(userParam);
        // 检查邮箱是否存在
        checkEmailExit(userParam.getMail(), userParam.getId());
        // 检查手机号是否存在
        checkTelephoneExit(userParam.getTelephone(), userParam.getId());

        SysUser before = sysUserMapper.queryById(userParam.getId());
        SysUser user = buildUser(userParam);

        user.setId(userParam.getId());
        int row = sysUserMapper.update(user);
        if (row > 0){
            sysLogService.saveUserLog(before, user);
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
        SysUser before = sysUserMapper.queryById(id);
        int row = sysUserMapper.deleteById(id);
        if (row > 0){
            sysLogService.saveUserLog(before, null);
        }
        return row > 0;
    }

    @Transactional
    @Override
    public int save(UserParam userParam) {
        // 校验参数
        BeanValidation.check(userParam);
        // 检查邮箱是否存在
        checkEmailExit(userParam.getMail(), userParam.getId());
        // 检查手机号是否存在
        checkTelephoneExit(userParam.getTelephone(), userParam.getId());


        SysUser user = buildUser(userParam);
        // 设置随机密码，并且使用 md5 加密
        user.setPassword(MD5Util.encrypt(PasswordUtil.getRandomPassword()));
        // TODO:发送邮件确认信息

        int row = sysUserMapper.insert(user);
        if (row > 0){
            sysLogService.saveUserLog(null, user);
        }
        return row;
    }

    @Override
    public SysUser findByKeyword(String username) {
        SysUser user = sysUserMapper.findByKeyword(username);
        return user;
    }

    @Override
    public PageInfo<SysUser> queryByDeptId(Long deptId) {

        List<SysUser> users = sysUserMapper.queryByDeptId(deptId);

        return new PageInfo<>(users);
    }

    @Override
    public List<SysUser> queryByRoleId(Long roleId) {
        return sysUserMapper.queryByRoleId(roleId);
    }

    private SysUser buildUser(UserParam userParam) {
        SysUser user = SysUser.builder().mall(userParam.getMail()).deptId(userParam.getDeptId())
                .remark(userParam.getRemark()).status(userParam.getStatus())
                .telephone(userParam.getTelephone()).username(userParam.getUsername()).build();
        
        user.setOperator(RequestHolder.getCurrentUser().getUsername());
        user.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));

        user.setOperatorTime(LocalDateTime.now());
        return user;
    }

    private void checkTelephoneExit(String telephone, Long id) {
        if (sysUserMapper.countByTelephone(telephone, id) > 0) {
            throw new BaseException("手机号已经存在");
        }
    }

    private void checkEmailExit(String mall, Long id) {
        if (sysUserMapper.countByMail(mall, id) > 0){
            throw new BaseException("邮箱已经存在");
        }
    }
}
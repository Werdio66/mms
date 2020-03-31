package com.lx.mms.service.impl;

import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysAcl;
import com.lx.mms.mapper.SysAclMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  处理用户和角色，角色和权限之间的关系
 */
@Service
public class SysCoreService {

    @Autowired
    private SysAclMapper sysAclMapper;


    public List<SysAcl> getCurrentUserAclList(){
        Long userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);
    }

    /**
     *  获取角色的权限列表
     * @param roleId        角色 id
     * @return              角色所有的权限
     */
    public List<SysAcl> getRoleAclList(Long roleId){
        return sysAclMapper.getRoleAclList(roleId);
    }

    /**
     *  查询用户的权限
     * @param userId    用户 id
     * @return          用户所有的权限
     */
    public List<SysAcl> getUserAclList(Long userId){
        if (isSuperAdmin()){

            return sysAclMapper.queryAll();
        }

        List<SysAcl> aclList = sysAclMapper.getUserAclList(userId);

        return aclList;
    }

    /**
     *  是否为超级管理员
     * @return
     */
    public boolean isSuperAdmin(){
        return true;
    }
}

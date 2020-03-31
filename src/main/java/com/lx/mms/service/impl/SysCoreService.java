package com.lx.mms.service.impl;

import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysAcl;
import com.lx.mms.mapper.SysAclMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *  处理用户和角色，角色和权限之间的关系
 */
@Service
public class SysCoreService {

    @Autowired
    private SysAclMapper sysAclMapper;


    public List<SysAcl> getCurrentUserAclList(){
        if (isSuperAdmin()){
            return sysAclMapper.queryAll();
        }
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
//        if (isSuperAdmin()){
//            return sysAclMapper.queryAll();
//        }

        return sysAclMapper.getUserAclList(userId);
    }

    /**
     *  是否为超级管理员
     */
    public boolean isSuperAdmin(){
        if ("superadmin".equals(RequestHolder.getCurrentUser().getUsername())){
            return true;
        }
        return false;
    }

    public boolean hasUrlAcl(String url) {

        if (isSuperAdmin()){
            return true;
        }

        // 查询当前请求 url 是否在数据库中，如果没有，说明并未对这个 url 做权限控制，可以访问
        List<SysAcl> aclList = sysAclMapper.getByUrl(url);

        if (CollectionUtils.isEmpty(aclList)){
            return true;
        }

        // 获取当前用户的权限列表
        List<SysAcl> currentUserAclList = getCurrentUserAclList();
        // 存放当前用户权限的 id
        Set<Long> userIdList = currentUserAclList.stream().map(SysAcl::getId).collect(Collectors.toSet());

        for (SysAcl acl : aclList) {

            if (acl == null || acl.getStatus() != 1){
                continue;
            }
            // 判断当前用户是否有权限访问
            if (userIdList.contains(acl.getId())){
                return true;
            }
        }

        return false;
    }
}

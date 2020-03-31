package com.lx.mms.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.lx.mms.dto.AclDto;
import com.lx.mms.dto.AclModuleLevelDto;
import com.lx.mms.dto.DeptLevelDto;
import com.lx.mms.entity.SysAcl;
import com.lx.mms.entity.SysAclModule;
import com.lx.mms.entity.SysDept;
import com.lx.mms.mapper.SysAclMapper;
import com.lx.mms.mapper.SysAclModuleMapper;
import com.lx.mms.mapper.SysDeptMapper;
import com.lx.mms.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysTreeService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysAclModuleMapper aclModuleMapper;

    @Autowired
    private SysCoreService sysCoreService;

    @Autowired
    private SysAclMapper sysAclMapper;

    /**
     *  获取指定角色的权限列表
     * @param roleId
     * @return
     */
    public List<AclModuleLevelDto> roleTree(Long roleId){
        // 获取当前登录用户的权限
        List<SysAcl> userAclList = sysCoreService.getCurrentUserAclList();
        // 获取当前角色的权限列表
        List<SysAcl> roleAclList = sysCoreService.getRoleAclList(roleId);

        Set<Long> userAclIdSet = userAclList.stream().map(SysAcl::getId).collect(Collectors.toSet());
        Set<Long> roleAclIdSet = roleAclList.stream().map(SysAcl::getId).collect(Collectors.toSet());

        // 存放适配后的权限列表
        List<AclDto> aclDtoList = new ArrayList<>();

        List<SysAcl> allAcl = sysAclMapper.queryAll();

        for (SysAcl acl : allAcl) {
            AclDto aclDto = AclDto.adapt(acl);
            // 判断当前用户是否拥有这个权限
            if (userAclIdSet.contains(acl.getId())){
                aclDto.setHasAcl(true);
            }
            // 当前角色是否已经分配了这个权限
            if (roleAclIdSet.contains(acl.getId())){
                aclDto.setChecked(true);
            }

            aclDtoList.add(aclDto);
        }

        return aclListToTree(aclDtoList);
    }

    /**
     * 将权限列表转换成树
     *
     * @param aclDtoList
     * @return
     */
    private List<AclModuleLevelDto> aclListToTree(List<AclDto> aclDtoList) {

        // 拿到权限模块列表
        List<AclModuleLevelDto> aclModuleLevelList = aclModelTree();
        // 存储权限模块下的权限点信息
        Multimap<Long, AclDto> moduleIdAclMap = ArrayListMultimap.create();

        for(AclDto acl : aclDtoList) {
            // 当前权限是否有效
            if (acl.getStatus() == 1) {
                moduleIdAclMap.put(acl.getAclModuleId(), acl);
            }
        }

        bindAclsWithOrder(aclModuleLevelList, moduleIdAclMap);
        return aclModuleLevelList;
    }

    private void bindAclsWithOrder(List<AclModuleLevelDto> aclModuleLevelList, Multimap<Long, AclDto> moduleIdAclMap) {
        if (CollectionUtils.isEmpty(aclModuleLevelList)) {
            return;
        }
        // 将权限点挂载到权限模块上
        for (AclModuleLevelDto dto : aclModuleLevelList) {
            // 取出当前权限模块所有的权限点信息
            List<AclDto> aclDtoList = (List<AclDto>)moduleIdAclMap.get(dto.getId());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(aclDtoList)) {
                // 对权限点排序
                aclDtoList.sort(Comparator.comparingInt(SysAcl::getSeq));
                // 挂载
                dto.setAclList(aclDtoList);
            }
            // 递归处理当前权限模块的子模块
            bindAclsWithOrder(dto.getAclModuleList(), moduleIdAclMap);
        }
    }

    public List<DeptLevelDto> deptTree(){
        // 查询所有的部门
        List<SysDept> deptList = sysDeptMapper.queryAll();
        // 存放 dto 对象
        List<DeptLevelDto> dtoList = Lists.newArrayList();

        // 将所有的部门转换为 dto 对象
        for (SysDept dept : deptList) {
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            // 存入 list 中
            dtoList.add(dto);
        }

        // 构建树
        return deptToTree(dtoList);
    }


    /**
     *  将部门构建成一颗层级树
     * @param deptLevelDtoList      所有的部门
     * @return                      树状的部门
     */
    private List<DeptLevelDto> deptToTree(List<DeptLevelDto> deptLevelDtoList){
        //
        if (CollectionUtils.isEmpty(deptLevelDtoList)){
            return Lists.newArrayList();
        }
        // 存储每一个层级的部门
        // Multimap<String, DeptLevelDto> 相当于 Map<String, List<DeptLevelDto>>
        Multimap<String, DeptLevelDto> levelDtoMultimap = ArrayListMultimap.create();
        // 存放根结点的部门
        List<DeptLevelDto> roots = Lists.newArrayList();
        // 遍历所有的部门
        for (DeptLevelDto deptLevelDto : deptLevelDtoList) {
            // 将每一个层级的部门对象存入同一个集合中
            levelDtoMultimap.put(deptLevelDto.getLevel(), deptLevelDto);
            if (deptLevelDto.getLevel().equals(LevelUtil.root)){
                // 将 root 结点存入 roots 中
                roots.add(deptLevelDto);
            }
        }

        // 对根结点排序，默认按照 seq 从小到大
        // Comparator.comparingInt(SysDept::getSeq)
        // 相当于：
        // (o1, o2) -> return o1.getSeq() - o2.getSeq();
        roots.sort((Comparator.comparingInt(SysDept::getSeq)));

        // 处理层级关系
        transformDeptTree(roots, LevelUtil.root, levelDtoMultimap);

        return roots;
    }

    /**
     *
     * @param deptLevelDtoList      所有的部门对象
     * @param level                 层级
     * @param levelDtoMultimap      每一个 key 对应这一层级的部门集合
     */
    private void transformDeptTree(List<DeptLevelDto> deptLevelDtoList, String level, Multimap<String, DeptLevelDto> levelDtoMultimap) {

        for (int i = 0; i < deptLevelDtoList.size(); i++) {
            // 当前对象
            DeptLevelDto deptLevelDto = deptLevelDtoList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.caculatateLevel(level, deptLevelDto.getId());
            // 获取下一层级
            List<DeptLevelDto> levelDtos = (List<DeptLevelDto>)levelDtoMultimap.get(nextLevel);

            if (!CollectionUtils.isEmpty(levelDtos)){
                // 排序
                levelDtos.sort((Comparator.comparingInt(SysDept::getSeq)));
                // 设置下一级部门
                deptLevelDto.setDeptList(levelDtos);
                // 处理下一层
                transformDeptTree(levelDtos, nextLevel, levelDtoMultimap);
            }
        }
    }




    public List<AclModuleLevelDto> aclModelTree(){

        List<SysAclModule> aclModuleList = aclModuleMapper.queryAll();

        List<AclModuleLevelDto> dtoList = new ArrayList<>();

        for (SysAclModule aclModule : aclModuleList) {
            AclModuleLevelDto dto = AclModuleLevelDto.adapt(aclModule);
            dtoList.add(dto);
        }

        return aclModelToTree(dtoList);
    }

    private List<AclModuleLevelDto> aclModelToTree(List<AclModuleLevelDto> aclModuleLevelDtoList) {
        //
        if (CollectionUtils.isEmpty(aclModuleLevelDtoList)){
            return Lists.newArrayList();
        }
        // 存储每一个层级的部门
        // Multimap<String, DeptLevelDto> 相当于 Map<String, List<DeptLevelDto>>
        Multimap<String, AclModuleLevelDto> levelDtoMultimap = ArrayListMultimap.create();
        // 存放根结点的部门
        List<AclModuleLevelDto> roots = Lists.newArrayList();
        // 遍历所有的部门
        for (AclModuleLevelDto aclModuleLevelDto : aclModuleLevelDtoList) {
            // 将每一个层级的部门对象存入同一个集合中
            levelDtoMultimap.put(aclModuleLevelDto.getLevel(), aclModuleLevelDto);
            if (aclModuleLevelDto.getLevel().equals(LevelUtil.root)){
                // 将 root 结点存入 roots 中
                roots.add(aclModuleLevelDto);
            }
        }

        // 对根结点排序，默认按照 seq 从小到大
        // Comparator.comparingInt(SysDept::getSeq)
        // 相当于：
        // (o1, o2) -> return o1.getSeq() - o2.getSeq();
        roots.sort((Comparator.comparingInt(SysAclModule::getSeq)));

        // 处理层级关系
        transformAclModelTree(roots, LevelUtil.root, levelDtoMultimap);

        return roots;
    }

    private void transformAclModelTree(List<AclModuleLevelDto> aclModuleLevelDtoList, String level, Multimap<String, AclModuleLevelDto> levelDtoMultimap) {
        for (int i = 0; i < aclModuleLevelDtoList.size(); i++) {
            // 当前对象
            AclModuleLevelDto deptLevelDto = aclModuleLevelDtoList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.caculatateLevel(level, deptLevelDto.getId());
            // 获取下一层级
            List<AclModuleLevelDto> levelDtos = (List<AclModuleLevelDto>)levelDtoMultimap.get(nextLevel);

            if (!CollectionUtils.isEmpty(levelDtos)){
                // 排序
                levelDtos.sort((Comparator.comparingInt(SysAclModule::getSeq)));
                // 设置下一级部门
                deptLevelDto.setAclModuleList(levelDtos);
                // 处理下一层
                transformAclModelTree(levelDtos, nextLevel, levelDtoMultimap);
            }
        }
    }

}

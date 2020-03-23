package com.lx.mms.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.lx.mms.dto.DeptLevelDto;
import com.lx.mms.entity.SysDept;
import com.lx.mms.mapper.SysDeptMapper;
import com.lx.mms.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class SysTreeService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

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
}

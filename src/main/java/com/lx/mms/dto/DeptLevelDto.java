package com.lx.mms.dto;

import com.lx.mms.entity.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算部门的层级树
 */
@Getter
@Setter

public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> deptList = new ArrayList<>();

    public static DeptLevelDto adapt(SysDept dept){
        DeptLevelDto deptLevelDto = new DeptLevelDto();
        BeanUtils.copyProperties(dept, deptLevelDto);
        return deptLevelDto;
    }
}

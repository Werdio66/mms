package com.lx.mms;

import com.lx.mms.dto.DeptLevelDto;
import com.lx.mms.service.SysDeptService;
import com.lx.mms.service.impl.SysTreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = "classpath:spring.xml")
public class DeptTest {

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private SysDeptService sysDeptService;

    @Test
    void testTree(){
        List<DeptLevelDto> deptLevelDtos = sysTreeService.deptTree();
        for (DeptLevelDto deptLevelDto : deptLevelDtos) {
            System.out.println(deptLevelDto);
            System.out.println(deptLevelDto.getDeptList());
        }
    }

    @Test
    void save(){

    }
}

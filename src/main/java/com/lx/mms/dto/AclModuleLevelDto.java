package com.lx.mms.dto;

import com.lx.mms.entity.SysAclModule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算权限的层级树
 */
@Getter
@Setter
public class AclModuleLevelDto extends SysAclModule {

     private List<AclModuleLevelDto> aclModuleList = new ArrayList<>();

    public static AclModuleLevelDto adapt(SysAclModule aclModule){
        AclModuleLevelDto aclModuleLevelDto = new AclModuleLevelDto();
        BeanUtils.copyProperties(aclModule, aclModuleLevelDto);
        return aclModuleLevelDto;
    }
}

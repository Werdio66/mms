package com.lx.mms.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysRoleAcl)实体类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Data
public class SysRoleAcl implements Serializable {
    private static final long serialVersionUID = 896212226043572493L;
    /**
    * 主键 id
    */
    private Long id;
    /**
    * 角色 id
    */
    private Long roleId;
    /**
    * 权限 id
    */
    private Long aclId;
    /**
    * 操作者
    */
    private String operator;
    /**
    * 操作时间
    */
    private LocalDateTime operatorTime;
    /**
    * 操作者 ip
    */
    private String operatorIp;
}
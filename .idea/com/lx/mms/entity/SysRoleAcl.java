package com.lx.mms.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysRoleAcl)实体类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:51:42
 */
public class SysRoleAcl implements Serializable {
    private static final long serialVersionUID = -82923622031782492L;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAclId() {
        return aclId;
    }

    public void setAclId(Long aclId) {
        this.aclId = aclId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LocalDateTime getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(LocalDateTime operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

}
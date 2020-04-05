package com.lx.mms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysRoleAcl)实体类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operatorTime;
    /**
    * 操作者 ip
    */
    private String operatorIp;
}
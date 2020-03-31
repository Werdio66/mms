package com.lx.mms.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysRoleUser)实体类
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
public class SysRoleUser implements Serializable {
    private static final long serialVersionUID = 565829079865125485L;
    /**
    * 主键 id
    */
    private Long id;
    /**
    * 角色 id
    */
    private Long roleId;
    /**
    * 用户 id
    */
    private Long userId;
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
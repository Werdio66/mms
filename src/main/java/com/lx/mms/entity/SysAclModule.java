package com.lx.mms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysAclModule)实体类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class SysAclModule implements Serializable {
    private static final long serialVersionUID = 448919441767353409L;
    /**
    * 主键 id
    */
    private Long id;
    /**
    * 权限模块名称
    */
    private String name;
    /**
    * 父级模块权限 id
    */
    private Long parentId;
    /**
    * 权限模块层级
    */
    private String level;
    /**
    * 权限模块在当前层级的顺序
    */
    private Integer seq;
    /**
    * 备注
    */
    private String remark;
    /**
    * 操作者
    */
    private String operator;
    /**
    * 操作的时间
    */
    private LocalDateTime operatorTime;
    /**
    * 操作者的 ip
    */
    private String opetatorIp;
    /**
    * 状态，0：冻结，1：正常
    */
    private Integer status;
}
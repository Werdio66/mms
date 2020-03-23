package com.lx.mms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysDept)实体类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDept implements Serializable {
    private static final long serialVersionUID = 567235509434154344L;
    /**
    * 主键 id
    */
    private Long id;
    /**
    * 部门名称
    */
    private String name;
    /**
    * 父级部门 id
    */
    private Long parentId;
    /**
    * 部门层级
    */
    private String level;
    /**
    * 部门在当前层级的顺序
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
}
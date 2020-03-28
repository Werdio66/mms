package com.lx.mms.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysAcl)实体类
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
public class SysAcl implements Serializable {
    private static final long serialVersionUID = -35462858038104638L;
    /**
    * 主键 id
    */
    private Long id;
    /**
    * 权限名称
    */
    private String name;
    /**
    * 权限模块 id
    */
    private Long aclModuleId;
    /**
    * 权限码
    */
    private String code;
    /**
    * 请求的 url
    */
    private String url;
    /**
    * 类型，1：菜单，2：按钮，3：其他
    */
    private Integer type;
    /**
    * 状态，1：正常，0：冻结
    */
    private Integer status;
    /**
    * 权限在当前模块的顺序
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
    * 操作者 ip
    */
    private String operatorIp;
}
package com.lx.mms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysRole)实体类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements Serializable {
    private static final long serialVersionUID = -91361965227631809L;
    /**
    * 主键 id
    */
    private Long id;
    /**
    * 角色名称
    */
    private String name;
    /**
    * 角色类型，1：管理员，2：其他
    */
    private Integer type;
    /**
    * 状态，1：正常，0：冻结
    */
    private Integer status;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operatorTime;
    /**
    * 操作者 ip
    */
    private String operatorIp;
}
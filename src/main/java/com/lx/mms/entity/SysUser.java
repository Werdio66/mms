package com.lx.mms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -17889526287078069L;
    /**
    * 主键 id
    */
    private Long id;
    /**
    * 用户名称
    */
    private String username;
    /**
    * 手机号
    */
    private String telephone;
    /**
    * 邮箱
    */
    private String mall;
    /**
    * 密码
    */
    private String password;
    /**
    * 用户的部门 id
    */
    private Long deptId;
    /**
    * 用户的状态，1 ：正常，0：冻结，2删除
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
    private LocalDateTime operatorTime;
    /**
    * 操作者的 ip
    */
    private String operatorIp;
}
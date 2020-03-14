package com.lx.mms.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysLog)实体类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = 570061300212067044L;
    /**
    * 主键 id
    */
    private Long id;
    /**
    * 权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系
    */
    private Integer type;
    /**
    * 基于 type 的指定对象的 id，比如用户，部门，权限等的 id
    */
    private Long targetId;
    /**
    * 旧的值
    */
    private String oldvalue;
    /**
    * 新的值
    */
    private String newvalue;
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
    /**
    * 当前是否恢复过，0：没有，1：恢复过
    */
    private Integer status;
}
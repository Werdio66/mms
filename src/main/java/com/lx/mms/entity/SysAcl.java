package com.lx.mms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
// id 相等才是相同的对象
@EqualsAndHashCode(of = "id")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime operatorTime;
    /**
    * 操作者 ip
    */
    private String operatorIp;
}
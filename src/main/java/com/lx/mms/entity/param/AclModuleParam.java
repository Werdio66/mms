package com.lx.mms.entity.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AclModuleParam {

     /**
    * 主键 id
    */
    private Long id;
    /**
    * 权限模块名称
    */
    @NotBlank(message = "权限名称不能为空")
    @Length(max = 20, message = "权限模块名称长度最多为 20 个字符")
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
    @NotNull(message = "权限模块在当前层级的顺序不能为空")
    private Integer seq;
    /**
    * 备注
    */
    @Length(max = 200, message = "备注最多 200 个字符")
    private String remark;

    /**
     * 状态，0：冻结，1：正常
     */
    @NotNull(message = "权限模块状态不能为空")
    @Min(value = 0, message = "权限模块状态最小为 0")
    @Max(value = 1, message = "权限模块状态最大为 1")
    private Integer status;

}

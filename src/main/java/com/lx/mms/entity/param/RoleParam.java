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
public class RoleParam {

    /**
    * 主键 id
    */
    private Long id;
    /**
    * 角色名称
    */
    @Length(max = 20, min = 1, message = "角色名称长度在 1 - 20 个字符之间")
    @NotBlank(message = "角色名称不能为空")
    private String name;
    /**
     * 角色类型，1：管理员，2：其他
     */
    @NotNull(message = "角色类型不能空")
    @Max(value = 2, message = "角色类型不合法")
    @Min(value = 1, message = "角色类型不合法")
    private Integer type = 1;
    /**
    * 状态，1：正常，0：冻结
    */
    @NotNull(message = "角色状态不能空")
    @Max(value = 1, message = "角色状态不合法")
    @Min(value = 0, message = "角色状态不合法")
    private Integer status;
    /**
    * 备注
    */
    @Length(max = 200, message = "角色的备注不能超过 200 个字符")
    private String remark;

}

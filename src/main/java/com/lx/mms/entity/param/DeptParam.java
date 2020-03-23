package com.lx.mms.entity.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class DeptParam {

    /**
    * 主键 id
    */
    private Long id;

    /**
    * 部门名称
    */
    @NotBlank(message = "部门名称不能为空")
    @Length(max = 20, min = 2, message = "部门名称的长度为 2 - 20 个字之间")
    private String name;

    /**
    * 父级部门 id
    */
    private Long parentId;

    /**
    * 部门在当前层级的顺序
    */
    @NotNull(message = "部门当前的层级顺序不能为空")
    private Integer seq;

    /**
    * 备注
    */
    @Length(max = 150, message = "备注最多 150 个字")
    private String remark;
}

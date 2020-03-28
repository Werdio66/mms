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
public class AclParam {

    /**
    * 主键 id
    */
    private Long id;
    /**
    * 权限名称
    */
    @NotBlank(message = "权限名称不能为空")
    @Length(max = 20, message = "权限名称最大为 20 个字符")
    private String name;
    /**
    * 权限模块 id
    */
    @NotNull(message = "权限模块不能为空")
    private Long aclModuleId;
    /**
    * 请求的 url
    */
    @Length(min = 6, max = 100, message = "权限点URL长度需要在6-100个字符之间")
    private String url;
    /**
    * 类型，1：菜单，2：按钮，3：其他
    */
    @NotNull(message = "必须指定权限点的类型")
    @Min(value = 1, message = "权限点类型不合法")
    @Max(value = 3, message = "权限点类型不合法")
    private Integer type;
    /**
    * 状态，1：正常，0：冻结
    */
    @NotNull(message = "必须指定权限点的状态")
    @Min(value = 0, message = "权限点状态不合法")
    @Max(value = 1, message = "权限点状态不合法")
    private Integer status;
    /**
    * 权限在当前模块的顺序
    */
    @NotNull(message = "必须指定权限点的展示顺序")
    private Integer seq;
    /**
    * 备注
    */
    @Length(max = 200, message = "权限点备注长度需要在200个字符以内")
    private String remark;

}

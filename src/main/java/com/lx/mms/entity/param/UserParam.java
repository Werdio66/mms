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
public class UserParam {

    /**
     * 主键 id
     */
    private Long id;
    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称不可以为空")
    @Length(min = 1, max = 20, message = "用户名长度在 20 以内")
    private String username;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号码不符合规则")
    private String telephone;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String mail;
    /**
     * 用户的部门 id
     */
    @NotNull(message = "用户的部门不能为空")
    private Long deptId;
    /**
     * 用户的状态，1 ：正常，0：冻结，2删除
     */
    @Max(value = 2, message = "数据最大为 2")
    @Min(value = 0, message = "数据最小为 1")
    private Integer status;
    /**
     * 备注
     */
    @Length(max = 200, message = "备注长度最多 200 个字符")
    private String remark;
}

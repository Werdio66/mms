package com.lx.mms;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TestEntity {

    @NotBlank
    private String name;

    @NotNull
    private Integer age;
}

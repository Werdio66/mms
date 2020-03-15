package com.lx.mms;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Data
public class Dog {

    private String name;

    private Integer age;
}

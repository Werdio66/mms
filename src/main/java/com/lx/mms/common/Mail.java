package com.lx.mms.common;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mail {

    private String subject;
    private String message;
    private Set<String> receivers;
}

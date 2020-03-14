package com.lx.mms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWord {

    @Autowired
    private Dog dog;

    private HelloWord(){}

}

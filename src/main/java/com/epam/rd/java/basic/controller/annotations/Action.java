package com.epam.rd.java.basic.controller.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String url();
    String method();
}
package com.epam.rd.java.basic.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    REGISTERED("registered"),
    PAID("paid"),
    CANCELED("canceled");

    private final String name;

}

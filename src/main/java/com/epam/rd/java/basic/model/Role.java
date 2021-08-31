package com.epam.rd.java.basic.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("admin"),
    USER("user"),
    UNREGISTERED("unregistered");

    private final String name;

}

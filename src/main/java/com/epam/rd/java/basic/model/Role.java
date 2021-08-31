package com.epam.rd.java.basic.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("admin"),
    USER("user"),
    UNREGISTERED("unregistered");

    private int id;
    private final String name;

    public static Role createRole(String name, int id) {
        Role role = UNREGISTERED;
        if (name == null) {
            return role;
        }
        if (name.equalsIgnoreCase(ADMIN.name)) {
            role = Role.ADMIN;
        } else if (name.equalsIgnoreCase(USER.name)) {
            role = Role.USER;
        }
        role.id = id;
        return role;
    }
}

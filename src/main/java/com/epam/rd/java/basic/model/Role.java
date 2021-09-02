package com.epam.rd.java.basic.model;

import java.io.Serializable;

public enum Role implements Serializable, Entity {

    ADMIN("admin"),
    USER("user"),
    UNREGISTERED("unregistered");

    private int id;
    private final String name;

    Role(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Role createRole(String name, int id) {
        Role role = UNREGISTERED;
        if (name == null) {
            return role;
        }
        if (name.equalsIgnoreCase(ADMIN.name)) {
            role = ADMIN;
        }
        if (name.equalsIgnoreCase(USER.name)) {
            role = USER;
        }
        role.id = id;
        return role;
    }

}

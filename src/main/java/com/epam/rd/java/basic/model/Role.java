package com.epam.rd.java.basic.model;

import java.io.Serializable;

public enum Role implements Serializable {

    ADMIN("admin"),
    USER("user"),
    UNREGISTERED("unregistered");

    private int id;
    private String name;

    Role() {
        //POJO object
    }

    Role(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
            role = Role.ADMIN;
        } else if (name.equalsIgnoreCase(USER.name)) {
            role = Role.USER;
        }
        role.id = id;
        return role;
    }

}

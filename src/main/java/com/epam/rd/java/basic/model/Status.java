package com.epam.rd.java.basic.model;

import java.io.Serializable;

public enum Status implements Serializable, Entity {

    REGISTERED("registered"),
    PAID("paid"),
    CANCELED("canceled"),
    OPEN("open");

    private int id;
    private final String name;

    Status(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Status createStatus(String name, int id) {
        Status status = OPEN;
        if (name == null) {
            return status;
        }
        if (name.equalsIgnoreCase(REGISTERED.name)) {
            status = REGISTERED;
        }
        if (name.equalsIgnoreCase(PAID.name)) {
            status = PAID;
        }
        if (name.equalsIgnoreCase(CANCELED.name)) {
            status = CANCELED;
        }
        status.id = id;
        return status;
    }
}

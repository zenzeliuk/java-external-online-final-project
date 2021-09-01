package com.epam.rd.java.basic.model;

import java.io.Serializable;

public enum Status implements Serializable {

    REGISTERED("registered"),
    PAID("paid"),
    CANCELED("canceled");

    private int id;
    private String name;

    Status() {
    }

    Status(String name) {
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
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

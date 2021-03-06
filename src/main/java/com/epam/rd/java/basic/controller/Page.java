package com.epam.rd.java.basic.controller;

import java.util.Objects;

public class Page {

    private final String name;
    private DispatchType dispatchType;

    public Page(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public enum DispatchType {FORWARD, REDIRECT}

    public DispatchType getDispatchType() {
        return dispatchType;
    }

    public Page setDispatchType(DispatchType dispatchType) {
        this.dispatchType = dispatchType;
        return this;
    }

    public enum WebPath {

        HOME("/"),
        ADMIN_HOME("/admin/home"),
        ITEMS("/app/item?page=1"),
        LOGIN("/login/sign-in"),
        CART("/app/cart"),
        USERS("/admin/users?page=1"),
        CARTS("/admin/carts"),
        CREATE_ITEM("/admin/create-item");

        private final String path;

        WebPath(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return name.equals(page.name) &&
                dispatchType == page.dispatchType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dispatchType);
    }

    @Override
    public String toString() {
        return "Page{" +
                "name='" + name + '\'' +
                ", dispatchType=" + dispatchType +
                '}';
    }
}

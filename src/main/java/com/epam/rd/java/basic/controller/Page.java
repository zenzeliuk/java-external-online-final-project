package com.epam.rd.java.basic.controller;

import java.util.Objects;

public class Page {

    private String name;
    private boolean isRedirect;
    private DispatchType dispatchType;

    public Page(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isRedirect() {
        return isRedirect;
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
        ITEMS("/app/item"),
        CART("/app/cart");

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

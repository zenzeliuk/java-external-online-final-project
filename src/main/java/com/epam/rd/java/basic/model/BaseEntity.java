package com.epam.rd.java.basic.model;

import java.io.Serializable;
import java.util.Objects;


public abstract class BaseEntity implements Serializable, Entity {

    protected Integer id;

    protected BaseEntity() {
    }

    protected BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

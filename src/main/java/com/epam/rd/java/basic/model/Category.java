package com.epam.rd.java.basic.model;

import java.io.Serializable;
import java.util.Objects;

public class Category extends BaseEntity implements Serializable {

    private String name;

    public Category() {
    }

    public static Builder builder() {
        return new Category().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(int id) {
            Category.this.id = id;
            return this;
        }

        public Builder name(String name) {
            Category.this.name = name;
            return this;
        }

        public Category build() {
            return Category.this;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + getId() + '\'' +
                ", name='" + name +
                '}';
    }
}

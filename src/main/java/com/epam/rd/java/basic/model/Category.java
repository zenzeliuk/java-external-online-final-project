package com.epam.rd.java.basic.model;

import java.io.Serializable;
import java.util.Objects;

public class Category extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private int parentCategory;

    public Category() {
        //POJO object
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

        public Builder description(String description) {
            Category.this.description = description;
            return this;
        }

        public Builder parentCategory(int parentCategory) {
            Category.this.parentCategory = parentCategory;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(int parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return parentCategory == category.parentCategory
                && Objects.equals(name, category.name)
                && Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, parentCategory);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parentCategory=" + parentCategory +
                '}';
    }
}

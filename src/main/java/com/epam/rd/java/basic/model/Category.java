package com.epam.rd.java.basic.model;

import java.io.Serializable;
import java.util.Objects;

public class Category extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private Category parentCategory;

    public Category() {
    }

    public Category(int id, String name, String description, Category parentCategory) {
        super(id);
        this.name = name;
        this.description = description;
        this.parentCategory = parentCategory;
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

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name)
                && Objects.equals(description, category.description)
                && Objects.equals(parentCategory, category.parentCategory);
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

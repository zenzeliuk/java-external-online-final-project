package com.epam.rd.java.basic.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Item extends BaseEntity {

    private String name;
    private String code;
    private BigDecimal price;
    private String description;
    private int categoryId;

    public Item() {
        //POJO object
    }

    public static Builder builder() {
        return new Item().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(int id) {
            Item.this.id = id;
            return this;
        }

        public Builder name(String name) {
            Item.this.name = name;
            return this;
        }

        public Builder code(String code) {
            Item.this.code = code;
            return this;
        }

        public Builder price(BigDecimal price) {
            Item.this.price = price;
            return this;
        }

        public Builder description(String description) {
            Item.this.description = description;
            return this;
        }

        public Builder categoryId(int categoryId) {
            Item.this.categoryId = categoryId;
            return this;
        }

        public Item build() {
            return Item.this;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return categoryId == item.categoryId
                && Objects.equals(name, item.name)
                && Objects.equals(code, item.code)
                && Objects.equals(price, item.price)
                && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, code, price, description, categoryId);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}

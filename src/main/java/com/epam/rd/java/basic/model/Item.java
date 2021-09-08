package com.epam.rd.java.basic.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Item extends BaseEntity {

    private int categoryId;
    private int brandId;
    private int colorId;
    private int count;
    private String name;
    private String image;
    private BigDecimal price;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Item() {
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

        public Builder categoryId(int categoryId) {
            Item.this.categoryId = categoryId;
            return this;
        }

        public Builder brandId(int brandId) {
            Item.this.brandId = brandId;
            return this;
        }

        public Builder colorId(int colorId) {
            Item.this.colorId = colorId;
            return this;
        }

        public Builder name(String name) {
            Item.this.name = name;
            return this;
        }

        public Builder count(int count) {
            Item.this.count = count;
            return this;
        }

        public Builder image(String image) {
            Item.this.image = image;
            return this;
        }

        public Builder price(BigDecimal price) {
            Item.this.price = price;
            return this;
        }

        public Builder createTime(Timestamp createTime) {
            Item.this.createTime = createTime;
            return this;
        }

        public Builder updateTime(Timestamp updateTime) {
            Item.this.updateTime = updateTime;
            return this;
        }

        public Item build() {
            return Item.this;
        }

    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return categoryId == item.categoryId
                && brandId == item.brandId
                && colorId == item.colorId
                && count == item.count
                && Objects.equals(name, item.name)
                && Objects.equals(image, item.image)
                && Objects.equals(price, item.price)
                && Objects.equals(createTime, item.createTime)
                && Objects.equals(updateTime, item.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoryId, brandId, colorId, name, count, image, price, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", colorId=" + colorId +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

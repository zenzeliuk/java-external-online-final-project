package com.epam.rd.java.basic.model;

import java.io.Serializable;
import java.util.Objects;

public class ItemDetails extends BaseEntity implements Serializable {

    Integer categoryId;
    Integer brandId;
    Integer colorId;

    public ItemDetails() {
    }

    public static Builder builder() {
        return new ItemDetails().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder itemId(int id) {
            ItemDetails.this.id = id;
            return this;
        }

        public Builder categoryId(int categoryId) {
            ItemDetails.this.categoryId = categoryId;
            return this;
        }

        public Builder brandId(int brandId) {
            ItemDetails.this.brandId = brandId;
            return this;
        }

        public Builder colorId(int colorId) {
            ItemDetails.this.colorId = colorId;
            return this;
        }

        public ItemDetails build() {
            return ItemDetails.this;
        }
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", colorId=" + colorId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItemDetails that = (ItemDetails) o;
        return Objects.equals(categoryId, that.categoryId) && Objects.equals(brandId, that.brandId) && Objects.equals(colorId, that.colorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoryId, brandId, colorId);
    }
}

package com.epam.rd.java.basic.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class CartItem extends BaseEntity implements Serializable {

    private Cart cart;
    private Item item;
    private BigDecimal price;
    private int countItem;
    private Timestamp createTime;
    private Timestamp updateTime;

    public CartItem() {
        //POJO object
    }

    public static Builder builder() {
        return new CartItem().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(int id) {
            CartItem.this.id = id;
            return this;
        }

        public Builder cart(Cart cart) {
            CartItem.this.cart = cart;
            return this;
        }

        public Builder item(Item item) {
            CartItem.this.item = item;
            return this;
        }

        public Builder price(BigDecimal price) {
            CartItem.this.price = price;
            return this;
        }

        public Builder countItem(int countItem) {
            CartItem.this.countItem = countItem;
            return this;
        }

        public Builder createTime(Timestamp createTime) {
            CartItem.this.createTime = createTime;
            return this;
        }

        public Builder updateTime(Timestamp updateTime) {
            CartItem.this.updateTime = updateTime;
            return this;
        }

        public CartItem build() {
            return CartItem.this;
        }
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCountItem() {
        return countItem;
    }

    public void setCountItem(int countItem) {
        this.countItem = countItem;
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
        CartItem cartItem = (CartItem) o;
        return countItem == cartItem.countItem
                && Objects.equals(cart, cartItem.cart)
                && Objects.equals(item, cartItem.item)
                && Objects.equals(price, cartItem.price)
                && Objects.equals(createTime, cartItem.createTime)
                && Objects.equals(updateTime, cartItem.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cart, item, price, countItem, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + getId() +
                ", cart=" + cart +
                ", item=" + item +
                ", price=" + price +
                ", countItem=" + countItem +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

package com.epam.rd.java.basic.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Cart extends BaseEntity implements Serializable {

    private Status status;
    private User customer;
    private User userApproved;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Cart() {
        //POJO object
    }

    public static Builder builder() {
        return new Cart().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(int id) {
            Cart.this.id = id;
            return this;
        }

        public Builder status(Status status) {
            Cart.this.status = status;
            return this;
        }

        public Builder customer(User customer) {
            Cart.this.customer = customer;
            return this;
        }

        public Builder userApproved(User userApproved) {
            Cart.this.userApproved = userApproved;
            return this;
        }

        public Builder createTime(Timestamp createTime) {
            Cart.this.createTime = createTime;
            return this;
        }

        public Builder updateTime(Timestamp updateTime) {
            Cart.this.updateTime = updateTime;
            return this;
        }

        public Cart build() {
            return Cart.this;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getUserApproved() {
        return userApproved;
    }

    public void setUserApproved(User userApproved) {
        this.userApproved = userApproved;
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
        Cart cart = (Cart) o;
        return status == cart.status
                && Objects.equals(customer, cart.customer)
                && Objects.equals(userApproved, cart.userApproved)
                && Objects.equals(createTime, cart.createTime)
                && Objects.equals(updateTime, cart.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status, customer, userApproved, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + getId() +
                ", status=" + status +
                ", customer=" + customer +
                ", userApproved=" + userApproved +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

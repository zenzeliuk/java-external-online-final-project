package com.epam.rd.java.basic.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Cart extends BaseEntity implements Serializable {

    private Integer statusId;
    private Integer userId;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Cart() {
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

        public Builder statusId(Integer statusId) {
            Cart.this.statusId = statusId;
            return this;
        }

        public Builder userId(Integer userId) {
            Cart.this.userId = userId;
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return statusId == cart.statusId && userId == cart.userId && Objects.equals(createTime, cart.createTime) && Objects.equals(updateTime, cart.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), statusId, userId, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", statusId=" + statusId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

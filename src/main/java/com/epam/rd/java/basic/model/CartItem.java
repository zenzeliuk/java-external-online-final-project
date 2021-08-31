package com.epam.rd.java.basic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class CartItem extends BaseEntity {

    private Cart cart;
    private Item item;
    private BigDecimal price;
    private int countItem;
    private Timestamp createTime;
    private Timestamp updateTime;

}

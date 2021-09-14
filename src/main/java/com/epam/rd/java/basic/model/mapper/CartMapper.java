package com.epam.rd.java.basic.model.mapper;

import com.epam.rd.java.basic.model.Cart;
import com.epam.rd.java.basic.model.Status;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.model.dto.CartDTO;

public class CartMapper {
    public static CartDTO toCartDTO(Cart cart, User user, Status status) {
        return CartDTO.builder()
                .id(String.valueOf(cart.getId()))
                .statusName(status.getName())
                .createTime(cart.getCreateTime())
                .updateTime(cart.getUpdateTime())
                .build();
    }
}

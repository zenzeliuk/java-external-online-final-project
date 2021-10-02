package com.epam.rd.java.basic.model.mapper;

import com.epam.rd.java.basic.model.CartItem;
import com.epam.rd.java.basic.model.Item;
import com.epam.rd.java.basic.model.dto.CartItemDTO;

public class CartItemMapper {
    public static CartItemDTO toCartItemDTO(CartItem cartItem, Item item) {
        return CartItemDTO.builder()
                .id(String.valueOf(cartItem.getId()))
                .itemName(item.getName())
                .price(String.valueOf(cartItem.getPrice()))
                .count(String.valueOf(cartItem.getCountItem()))
                .build();
    }
}
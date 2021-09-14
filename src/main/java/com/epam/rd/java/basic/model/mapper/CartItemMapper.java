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

/*
public static ItemDTO toItemDTO(Item item) {
        return ItemDTO.builder()
                .id(String.valueOf(item.getId()))
                .name(item.getName())
                .price(String.valueOf(item.getPrice()))
//                .description(item.getColorId())
                .build();
    }

    public static List<ItemDTO> toItemDTOList(List<Item> itemList) {
        return itemList.stream()
                .map(ItemMapper::toItemDTO)
                .collect(Collectors.toList());
    }
 */
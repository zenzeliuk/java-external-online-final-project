package com.epam.rd.java.basic.model.mapper;

import com.epam.rd.java.basic.model.dto.ItemDTO;
import com.epam.rd.java.basic.model.Item;

import java.util.List;
import java.util.stream.Collectors;
/*
name;
price;
description;
categoryName;
 */
public class ItemMapper {
    public static ItemDTO toItemDTO(Item item) {
        return ItemDTO.builder()
                .id(String.valueOf(item.getId()))
                .name(item.getName())
                .price(String.valueOf(item.getPrice()))
                .description(item.getDescription())
                .build();
    }

    public static List<ItemDTO> toItemDTOList(List<Item> itemList) {
        return itemList.stream()
                .map(ItemMapper::toItemDTO)
                .collect(Collectors.toList());
    }
}

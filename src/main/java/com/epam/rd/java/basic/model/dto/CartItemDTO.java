package com.epam.rd.java.basic.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDTO {

    private String id;
    private String itemName;
    private String price;
    private String count;

}

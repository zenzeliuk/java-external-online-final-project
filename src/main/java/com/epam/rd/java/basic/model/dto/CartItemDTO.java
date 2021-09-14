package com.epam.rd.java.basic.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDTO {

    String id;
    String itemName;
    String price;
    String count;

}

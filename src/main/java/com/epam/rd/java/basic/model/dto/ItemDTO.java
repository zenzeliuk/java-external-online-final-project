package com.epam.rd.java.basic.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {

    private String id;
    private String name;
    private String price;
    private String description;
    private String category;

}

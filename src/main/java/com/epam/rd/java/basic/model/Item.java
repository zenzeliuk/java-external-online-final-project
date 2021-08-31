package com.epam.rd.java.basic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Item extends BaseEntity {

    private String name;
    private String code;
    private BigDecimal price;
    private String description;
    private Category category;

}

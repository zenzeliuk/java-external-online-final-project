package com.epam.rd.java.basic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Category extends BaseEntity {

    private String name;
    private String description;
    private Category parentCategory;

}

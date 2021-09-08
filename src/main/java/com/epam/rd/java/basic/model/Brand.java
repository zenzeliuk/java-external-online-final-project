package com.epam.rd.java.basic.model;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends BaseEntity implements Serializable {

    private String name;

}

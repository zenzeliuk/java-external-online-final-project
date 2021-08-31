package com.epam.rd.java.basic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserDetails extends BaseEntity {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private BigInteger phone;
    private int age;

}

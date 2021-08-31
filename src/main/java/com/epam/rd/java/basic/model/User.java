package com.epam.rd.java.basic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class User extends BaseEntity {

    private String login;
    private String password;
    private Role role;

}

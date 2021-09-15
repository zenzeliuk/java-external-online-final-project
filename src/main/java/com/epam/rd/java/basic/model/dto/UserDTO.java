package com.epam.rd.java.basic.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String idUser;
    private String login;
    private String roleName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String age;

}

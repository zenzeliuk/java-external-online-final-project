package com.epam.rd.java.basic.model.mapper;


import com.epam.rd.java.basic.model.Role;
import com.epam.rd.java.basic.model.User;
import com.epam.rd.java.basic.model.UserDetails;
import com.epam.rd.java.basic.model.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserDTO toUserDTO(User user, UserDetails userDetails, Role role) {
        UserDTO userDTO = UserDTO.builder()
                .idUser(String.valueOf(user.getId()))
                .login(user.getLogin())
                .roleName(role.getName())
                .build();
        if (userDetails != null) {
            userDTO.setFirstName(userDetails.getFirstName());
            userDTO.setLastName(userDetails.getLastName());
            userDTO.setEmail(userDetails.getEmail());
            userDTO.setPhone(String.valueOf(userDetails.getPhone()));
            userDTO.setAge(String.valueOf(userDetails.getAge()));
        }
        return userDTO;
    }

    public static List<UserDTO> toUserDTOList(List<User> userList, List<UserDetails> userDetailsList, List<Role> roleList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            Role role = null;
            UserDetails userDetails = null;
            for (Role r : roleList) {
                if (user.getRoleId() == r.getId()) {
                    role = r;
                }
            }
            for (UserDetails ud : userDetailsList) {
                if (user.getId() == ud.getId()) {
                    userDetails = ud;
                }
            }
            UserDTO userDTO = toUserDTO(user, userDetails, role);
            userDTOList.add(userDTO);
        }
        return userDTOList;

    }
}

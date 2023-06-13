package com.projectInter.application.mapper;

import com.projectInter.application.dto.PostRequestUserDTO;
import com.projectInter.domain.entities.User;


public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User toUser(PostRequestUserDTO user) {
        return User.builder()
                .name(user.getName())
                .birthday(user.getBirthday())
                .password(user.getPassword())
                .build();
    }

}

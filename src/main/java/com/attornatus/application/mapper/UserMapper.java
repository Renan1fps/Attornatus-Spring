package com.attornatus.application.mapper;

import com.attornatus.application.dto.PostRequestUser;
import com.attornatus.domain.entities.User;


public  class UserMapper {
    public static User toUser(PostRequestUser user){
        return User.builder()
                .name(user.getName())
                .birthday(user.getBirthday())
                .build();
    }

}

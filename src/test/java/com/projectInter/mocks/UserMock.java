package com.projectInter.mocks;

import com.projectInter.domain.entities.User;

import java.util.UUID;

public class UserMock {

    public static UUID id = UUID.randomUUID();

    public static User createdValidUser(){
        return User.builder()
                .name("any_value")
                .birthday("09/03/2000")
                .id(id)
                .build();
    }

    public static User createValidUser(){
        return User.builder()
                .name("any_value")
                .birthday("09/03/2000")
                .password("any_value")
                .build();
    }
}

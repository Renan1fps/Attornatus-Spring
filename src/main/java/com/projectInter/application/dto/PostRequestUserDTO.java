package com.projectInter.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestUserDTO {

    private String name;
    private String birthday;
    private String password;
    private String passwordConfirmation;
}

package com.projectInter.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostAddressDTO {

    private String cep;
    private Integer number;
    private String street;
    private String city;
    @JsonProperty(value = "isMain")
    private boolean isMain;
}

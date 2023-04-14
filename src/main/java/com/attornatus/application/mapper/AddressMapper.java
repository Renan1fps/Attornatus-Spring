package com.attornatus.application.mapper;

import com.attornatus.application.dto.PostAddress;
import com.attornatus.domain.entities.Address;

public class AddressMapper {

    public static Address toAddress(PostAddress address){
        return Address.builder()
                .isMain(address.isMain())
                .city(address.getCity())
                .cep(address.getCep())
                .street(address.getStreet())
                .number(address.getNumber())
                .build();
    }
}

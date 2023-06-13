package com.projectInter.application.mapper;

import com.projectInter.application.dto.PostAddressDTO;
import com.projectInter.domain.entities.Address;

public class AddressMapper {

    private AddressMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Address toAddress(PostAddressDTO address) {
        return Address.builder()
                .isMain(address.isMain())
                .city(address.getCity())
                .cep(address.getCep())
                .street(address.getStreet())
                .number(address.getNumber())
                .build();
    }
}

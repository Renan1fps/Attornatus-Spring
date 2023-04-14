package com.attornatus.application.usecases;

import com.attornatus.application.exception.BadRequestException;
import com.attornatus.domain.entities.Address;
import com.attornatus.domain.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateMainAddressUseCase {

    private AddressRepository addressRepository;

    public void execute(String id){

        Optional<Address> addressExists = this.addressRepository.findById(UUID.fromString(id));

        if (addressExists.isEmpty()) {
            throw new BadRequestException("Address not found");
        }

        Optional<Address> existsMain = this.addressRepository.findByIsMainTrue();

        existsMain.ifPresent(address -> {
            address.setIsMain(false);
            addressExists.get().setIsMain(true);
        });

    }

}

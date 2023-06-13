package com.projectInter.application.usecases;

import com.projectInter.application.exception.BadRequestException;
import com.projectInter.domain.entities.Address;
import com.projectInter.domain.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateMainAddressUseCase {

    private final AddressRepository addressRepository;

    public UpdateMainAddressUseCase(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

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

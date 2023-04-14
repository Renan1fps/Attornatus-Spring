package com.attornatus.application.usecases;

import com.attornatus.application.exception.BadRequestException;
import com.attornatus.domain.entities.Address;
import com.attornatus.domain.entities.User;
import com.attornatus.domain.repositories.AddressRepository;
import com.attornatus.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateUserAddressUseCase {

    private AddressRepository addressRepository;
    private UserRepository userRepository;

    public Address execute(Address addressToSave, String idUser){

        Optional<User> userExists = this.userRepository.findById(UUID.fromString(idUser));

        if (userExists.isEmpty()) {
            throw new BadRequestException("User not found");
        }

        Optional<Address> existsMain = this.addressRepository.findByIsMainTrue();

        if(existsMain.isPresent() && Boolean.TRUE.equals(addressToSave.getIsMain())){
            existsMain.get().setIsMain(false);
        }

        addressToSave.setUser(userExists.get());

        return this.addressRepository.save(addressToSave);
    }
}

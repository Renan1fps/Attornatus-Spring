package com.projectInter.application.usecases;

import com.projectInter.application.exception.BadRequestException;
import com.projectInter.domain.entities.Address;
import com.projectInter.domain.entities.User;
import com.projectInter.domain.repositories.AddressRepository;
import com.projectInter.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateUserAddressUseCase {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public CreateUserAddressUseCase(AddressRepository addressRepository, UserRepository userRepository){
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

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

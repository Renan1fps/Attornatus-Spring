package com.attornatus.application.usecases;

import com.attornatus.application.exception.BadRequestException;
import com.attornatus.domain.entities.Address;
import com.attornatus.domain.entities.User;
import com.attornatus.domain.repositories.AddressRepository;
import com.attornatus.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetAllAddressByIdUser {

    private UserRepository userRepository;
    private AddressRepository addressRepository;

    public List<Address> execute(String idUser){

        Optional<User> userExists = this.userRepository.findById(UUID.fromString(idUser));

        if (userExists.isEmpty()) {
            throw new BadRequestException("User not found");
        }

        return this.addressRepository.findByUser(userExists.get());
    }
}

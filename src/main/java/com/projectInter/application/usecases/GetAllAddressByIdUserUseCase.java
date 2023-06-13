package com.projectInter.application.usecases;

import com.projectInter.application.exception.BadRequestException;
import com.projectInter.domain.entities.Address;
import com.projectInter.domain.entities.User;
import com.projectInter.domain.repositories.AddressRepository;
import com.projectInter.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetAllAddressByIdUserUseCase {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public GetAllAddressByIdUserUseCase(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public List<Address> execute(String idUser) {

        Optional<User> userExists = this.userRepository.findById(UUID.fromString(idUser));

        if (userExists.isEmpty()) {
            throw new BadRequestException("User not found");
        }

        return this.addressRepository.findByUser(userExists.get());
    }
}

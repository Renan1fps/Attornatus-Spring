package com.projectInter.application.usecases;

import com.projectInter.application.exception.BadRequestException;
import com.projectInter.application.protocols.Encrypt;
import com.projectInter.domain.entities.User;
import com.projectInter.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final Encrypt crypto;

    public CreateUserUseCase(UserRepository userRepository, Encrypt crypto){
        this.userRepository = userRepository;
        this.crypto = crypto;
    }

    public User execute(User userToSave) {

        Optional<User> user = this.userRepository.findByName(userToSave.getName());

        if (user.isPresent()) {
            throw new BadRequestException("user already exists with same name");
        }

        userToSave.setId(UUID.randomUUID());
        userToSave.setPassword(this.crypto.crypto(userToSave.getPassword()));

        return this.userRepository.save(userToSave);
    }

}

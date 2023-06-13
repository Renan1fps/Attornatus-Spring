package com.projectInter.application.usecases;

import com.projectInter.application.exception.BadRequestException;
import com.projectInter.domain.entities.User;
import com.projectInter.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(User userToSave) {

        Optional<User> user = this.userRepository.findByName(userToSave.getName());

        if (user.isPresent()) {
            throw new BadRequestException("user already exists with same name");
        }

        userToSave.setId(UUID.randomUUID());

        return this.userRepository.save(userToSave);
    }

}

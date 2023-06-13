package com.projectInter.application.usecases;

import com.projectInter.application.exception.BadRequestException;
import com.projectInter.domain.entities.User;
import com.projectInter.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateUserUseCase {

    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User userToUpdate, String id) {

        Optional<User> userExists = this.userRepository.findById(UUID.fromString(id));

        if (userExists.isEmpty()) {
            throw new BadRequestException("User not found");
        }

        userExists.get().setName(userToUpdate.getName());
        userExists.get().setBirthday(userToUpdate.getBirthday());

        return userExists.get();
    }

}

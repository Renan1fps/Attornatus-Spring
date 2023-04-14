package com.attornatus.application.usecases;

import com.attornatus.application.exception.BadRequestException;
import com.attornatus.domain.entities.User;
import com.attornatus.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateUserUseCase {

    private UserRepository userRepository;

    public User execute(User userToSave) {

        Optional<User> user = this.userRepository.findByName(userToSave.getName());

        if (user.isPresent()) {
            throw new BadRequestException("user already exists with same name");
        }

        userToSave.setId(UUID.randomUUID());

        return this.userRepository.save(userToSave);
    }

}

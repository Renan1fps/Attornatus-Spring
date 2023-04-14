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
public class UpdateUserUseCase {

    private UserRepository userRepository;

    public User execute(User userToUpdate, String id){

        Optional<User> userExists = this.userRepository.findById(UUID.fromString(id));

        if (userExists.isEmpty()) {
            throw new BadRequestException("User not found");
        }

        userExists.get().setName(userToUpdate.getName());
        userExists.get().setBirthday(userToUpdate.getBirthday());

        return  userExists.get();
    }

}

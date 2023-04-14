package com.attornatus.application.usecases;

import com.attornatus.domain.entities.User;
import com.attornatus.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListAllUsersUseCase {

    private UserRepository userRepository;

    public Page<User> execute(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }
}

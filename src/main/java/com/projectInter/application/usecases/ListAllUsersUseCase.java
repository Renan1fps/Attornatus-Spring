package com.projectInter.application.usecases;

import com.projectInter.domain.entities.User;
import com.projectInter.domain.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllUsersUseCase {

    private final UserRepository userRepository;

    public ListAllUsersUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Page<User> execute(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }
}

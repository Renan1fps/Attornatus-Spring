package com.projectInter.controller;

import com.projectInter.application.dto.PostRequestUserDTO;
import com.projectInter.application.mapper.UserMapper;
import com.projectInter.application.usecases.CreateUserUseCase;
import com.projectInter.application.usecases.GetUserByIdUseCase;
import com.projectInter.application.usecases.ListAllUsersUseCase;
import com.projectInter.application.usecases.UpdateUserUseCase;
import com.projectInter.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final ListAllUsersUseCase listAllUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, GetUserByIdUseCase getUserByIdUseCase,
                          ListAllUsersUseCase listAllUsersUseCase, UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.listAllUsersUseCase = listAllUsersUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody PostRequestUserDTO user) {
        User userSave = this.createUserUseCase.execute(UserMapper.toUser(user));
        return new ResponseEntity<>(userSave, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        User user = this.getUserByIdUseCase.execute(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<User>> listAll(Pageable pageable) {
        Page<User> response = this.listAllUsersUseCase.execute(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody PostRequestUserDTO user) {
        User response = this.updateUserUseCase.execute(UserMapper.toUser(user), id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

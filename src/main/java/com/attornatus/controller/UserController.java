package com.attornatus.controller;

import com.attornatus.application.dto.PostRequestUser;
import com.attornatus.application.mapper.UserMapper;
import com.attornatus.application.usecases.CreateUserUseCase;
import com.attornatus.application.usecases.GetUserById;
import com.attornatus.application.usecases.ListAllUsersUseCase;
import com.attornatus.application.usecases.UpdateUserUseCase;
import com.attornatus.domain.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserById getUserById;
    private final ListAllUsersUseCase listAllUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody PostRequestUser user) {
        User userSave = this.createUserUseCase.execute(UserMapper.toUser(user));
        return new ResponseEntity<>(userSave, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        User user = this.getUserById.execute(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<User>> listAll(Pageable pageable) {
        Page<User> response = this.listAllUsersUseCase.execute(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody PostRequestUser user) {
        User response = this.updateUserUseCase.execute(UserMapper.toUser(user), id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

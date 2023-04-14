package com.attornatus.controller;

import com.attornatus.application.dto.PostAddress;
import com.attornatus.application.mapper.AddressMapper;
import com.attornatus.application.usecases.CreateUserAddressUseCase;
import com.attornatus.application.usecases.GetAllAddressByIdUser;
import com.attornatus.application.usecases.UpdateMainAddressUseCase;
import com.attornatus.domain.entities.Address;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
@AllArgsConstructor
public class AddressController {

    private final CreateUserAddressUseCase createUserAddressUseCase;
    private final GetAllAddressByIdUser getAllAddressByIdUser;
    private final UpdateMainAddressUseCase updateMainAddressUseCase;

    @PostMapping()
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Address> create(@RequestBody PostAddress address, @RequestParam String idUser) {
        Address response = this.createUserAddressUseCase.execute(AddressMapper.toAddress(address), idUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Address>> getAllAddressByUser(@RequestParam String idUser) {
        List<Address> response = this.getAllAddressByIdUser.execute(idUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateMainAddress(@PathVariable String idAddress) {
        this.updateMainAddressUseCase.execute(idAddress);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

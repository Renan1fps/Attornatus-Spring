package com.attornatus.domain.repositories;

import com.attornatus.domain.entities.Address;
import com.attornatus.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByUser(User user);
    Optional<Address> findByIsMainTrue();
}

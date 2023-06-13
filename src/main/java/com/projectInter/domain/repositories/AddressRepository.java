package com.projectInter.domain.repositories;

import com.projectInter.domain.entities.Address;
import com.projectInter.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByUser(User user);
    Optional<Address> findByIsMainTrue();
}

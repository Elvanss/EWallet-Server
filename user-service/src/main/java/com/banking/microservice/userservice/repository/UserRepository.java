package com.banking.microservice.userservice.repository;

import com.banking.microservice.userservice.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.email =: email")
    Optional<User> findByEmail(String email);
}

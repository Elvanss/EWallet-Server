package com.banking.microservice.userservice.service;

import com.banking.microservice.userservice.domain.entities.User;
import com.banking.microservice.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        try {
            // Debug time console
            long start = System.currentTimeMillis();

            // Find all users
            List<User> users = this.userRepository.findAll();

            // Debug time console
            long end = System.currentTimeMillis();

            // Debug time console
            System.out.println("Database start at: " + start);
            System.out.println("Database end at: " + end);
            System.out.println("Database query time with ms: " +  (end - start));

            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}

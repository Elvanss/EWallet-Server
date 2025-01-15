package com.banking.microservice.userservice.service;

import com.banking.microservice.userservice.domain.dto.LoginResponse;
import com.banking.microservice.userservice.domain.entities.User;
import com.banking.microservice.userservice.repository.UserRepository;
import com.banking.microservice.userservice.security.UserDetailsImpl;
import com.banking.microservice.userservice.security.UserDetailsServiceImpl;
import com.banking.microservice.userservice.types.Roles;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtService jwtService;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetailsImpl getUserByEmail(String email) {
        // Debug time console
        long start = System.currentTimeMillis();

        // Find user by email
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Debug time console
        long end = System.currentTimeMillis();

        // Debug time console
        System.out.println("Database start at: " + start);
        System.out.println("Database end at: " + end);
        System.out.println("Database query time with ms: " +  (end - start));

        return UserDetailsImpl.build(user);
    }

    @Transactional
    public User getUserById (UUID id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public LoginResponse login (String email, String password) {
        // Validate user by email and password
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password", e);
        }

        // Generate token
        final UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(email);
        final String token = jwtService.generateToken(userDetails);
        final String userEmail = userDetails.getUsername();

        return new LoginResponse(token, userEmail);

    }

    @Transactional
    public User register (User user) {
        try {
            user.setRole(Roles.USER);
            user.setActive(true);
            return this.userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

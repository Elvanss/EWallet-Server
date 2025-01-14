package com.banking.microservice.userservice.security;

import com.banking.microservice.userservice.domain.entities.User;
import com.banking.microservice.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
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
}

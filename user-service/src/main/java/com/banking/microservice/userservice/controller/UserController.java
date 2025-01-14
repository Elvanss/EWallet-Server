package com.banking.microservice.userservice.controller;

import com.banking.microservice.userservice.domain.dto.UserDto;
import com.banking.microservice.userservice.domain.entities.User;
import com.banking.microservice.userservice.domain.mapper.UserMapper;
import com.banking.microservice.userservice.security.UserDetailsImpl;
import com.banking.microservice.userservice.service.AdminService;
import com.banking.microservice.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/email")
    public ResponseEntity<UserDetailsImpl> getUserByEmail(
            @RequestParam("email") String email
    ) {
        UserDetailsImpl user = this.userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/id")
    @ManagedOperation(description = "Get user by id")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(this.userMapper.toUserDto(this.userService.getUserById(id)));
    }
}

package com.banking.microservice.userservice.controller;

import com.banking.microservice.userservice.domain.dto.LoginRequest;
import com.banking.microservice.userservice.domain.dto.LoginResponse;
import com.banking.microservice.userservice.domain.dto.UserDto;
import com.banking.microservice.userservice.domain.entities.User;
import com.banking.microservice.userservice.domain.mapper.UserMapper;
import com.banking.microservice.userservice.service.AdminService;
import com.banking.microservice.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
public class AuthController {

    private final UserService userService;

    private final UserMapper userMapper;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(this.userService.login(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(User user) {
        User obj = this.userService.register(user);
        UserDto userDto = this.userMapper.toUserDto(obj);
        return ResponseEntity.ok(userDto);
    }

}

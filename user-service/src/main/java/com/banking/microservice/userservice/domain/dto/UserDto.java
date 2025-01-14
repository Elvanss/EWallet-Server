package com.banking.microservice.userservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private boolean isActive;
    private String role;
}
package com.banking.microservice.userservice.domain.entities;

import com.banking.microservice.userservice.types.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "fullname" , nullable = false)
    private String fullName;

    @Column(name = "phone" , nullable = false)
    private String phone;

    @Column(name = "is_active" , nullable = false)
    private boolean isActive;
    @Column(name = "role" , nullable = false)
    private Roles role;
}

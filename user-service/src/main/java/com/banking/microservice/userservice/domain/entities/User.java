package com.banking.microservice.userservice.domain.entities;

import com.banking.microservice.userservice.types.Roles;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
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

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;
}
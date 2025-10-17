package com.mybank.onboarding.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class User {
    private UUID id;
    private String fullName;
    private String document;
    private String email;
    private String phone;
    private String password;
    private String accountNumber;
    private String agency;
    private String bank;
    private LocalDate birthDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;
}

package com.mybank.onboarding.adapters.inbound.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsResponse {
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private String accountNumber;
    private String agency;
    private String bank;
    private LocalDate birthDate;
    private String document;
}

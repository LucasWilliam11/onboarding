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
public class UserListResponse {
    private UUID id;
    private String fullName;
    private String accountNumber;
    private String agency;
    private String bank;
}

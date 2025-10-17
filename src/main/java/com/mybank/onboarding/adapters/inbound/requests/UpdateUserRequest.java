package com.mybank.onboarding.adapters.inbound.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    @Size(min = 5, message = "Name must have at least 5 characters.")
    @Schema(description = "Nome completo do usuário", example = "John Doe")
    private String fullName;
    @Email(message = "Email is invalid.")
    @Schema(description = "Email do usuário", example = "john.doe@example.com")
    private String email;
    @Schema(description = "Telefone do usuário", example = "11999999999")
    private String phone;
}

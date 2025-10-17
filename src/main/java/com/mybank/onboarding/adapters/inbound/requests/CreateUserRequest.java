package com.mybank.onboarding.adapters.inbound.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    @NotBlank(message = "Name is required.")
    @NotNull(message = "Name is required.")
    @Size(min = 5, message = "Name must have at least 5 characters.")
    @Schema(description = "Nome completo do usuário", example = "John Doe")
    private String fullName;
    @NotBlank(message = "Document is required.")
    @NotNull(message = "Document is required.")
    @Schema(description = "Documento do usuário", example = "12345678901")
    private String document;
    @NotNull(message = "Birth Date is required.")
    @Schema(description = "Data de nascimento do usuário", example = "1990/01/01")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthDate;
    @NotBlank(message = "Email is required.")
    @NotNull(message = "Email is required.")
    @Email(message = "Email is invalid.")
    @Schema(description = "Email do usuário", example = "john.doe@example.com")
    private String email;
    @NotBlank(message = "Phone is required.")
    @NotNull(message = "Phone is required.")
    @Schema(description = "Telefone do usuário", example = "11999999999")
    private String phone;
    @NotBlank(message = "Password is required.")
    @NotNull(message = "Password is required.")
    @Pattern(regexp = "^\\d{6}$", message = "Password must be exactly 6 digits.")
    @Schema(description = "Senha do usuário", example = "123123")
    private String password;
}

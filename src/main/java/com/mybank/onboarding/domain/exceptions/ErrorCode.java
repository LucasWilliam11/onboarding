package com.mybank.onboarding.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("MB000", "Internal Error."),
    VALIDATION_ERROR("MB001", "Validation error."),
    DATABASE_ERROR("MB002", "Internal Error."),
    USER_ALREADY_EXISTS("MB003", "User already exists."),
    ENCRYPT_ERROR("MB004", "Internal Error."),
    USER_NOT_FOUND("MB005", "User not found.");

    private final String code;
    private final String message;
}

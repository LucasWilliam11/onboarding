package com.mybank.onboarding.domain.exceptions;

import static com.mybank.onboarding.domain.exceptions.ErrorCode.ENCRYPT_ERROR;

public class EncryptException extends BusinessException {
    public EncryptException(String message) {
        super(ENCRYPT_ERROR, message, 500);
    }
}

package com.mybank.onboarding.domain.exceptions;

import static com.mybank.onboarding.domain.exceptions.ErrorCode.DATABASE_ERROR;

public class DatabaseException extends BusinessException{
    public DatabaseException(String message) {
        super(DATABASE_ERROR, message, 500);
    }
}

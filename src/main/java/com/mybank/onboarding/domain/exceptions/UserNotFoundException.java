package com.mybank.onboarding.domain.exceptions;

import static com.mybank.onboarding.domain.exceptions.ErrorCode.USER_NOT_FOUND;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String message) {
        super(USER_NOT_FOUND, message, 404);
    }
}

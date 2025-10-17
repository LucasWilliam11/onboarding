package com.mybank.onboarding.domain.exceptions;

public class UserAlreadyExists extends BusinessException{
    public UserAlreadyExists(String message) {
        super(ErrorCode.USER_ALREADY_EXISTS, message, 422);
    }
}

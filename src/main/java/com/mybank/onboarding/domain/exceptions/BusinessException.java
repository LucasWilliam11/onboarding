package com.mybank.onboarding.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private ErrorCode error;
    private String message;
    private Integer httpStatus = 500;
}

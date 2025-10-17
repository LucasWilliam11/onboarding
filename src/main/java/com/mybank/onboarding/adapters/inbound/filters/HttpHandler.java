package com.mybank.onboarding.adapters.inbound.filters;

import com.mybank.onboarding.adapters.inbound.responses.ErrorResponse;
import com.mybank.onboarding.domain.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static com.mybank.onboarding.domain.exceptions.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.mybank.onboarding.domain.exceptions.ErrorCode.VALIDATION_ERROR;

@Slf4j
@ControllerAdvice
public class HttpHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessExceptions(BusinessException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponse.builder()
                .code(ex.getError().getCode())
                .messages(List.of(ex.getError().getMessage()))
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::formatFieldError)
                .collect(Collectors.toList());

        log.error("Validation failed: {}", errors);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(VALIDATION_ERROR.getCode())
                .messages(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(500).body(ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.getCode())
                .messages(List.of(INTERNAL_SERVER_ERROR.getMessage()))
                .build());
    }

    private String formatFieldError(FieldError fieldError) {
        return String.format("Field '%s': %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}

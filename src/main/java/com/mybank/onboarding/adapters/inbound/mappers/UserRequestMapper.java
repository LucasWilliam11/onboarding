package com.mybank.onboarding.adapters.inbound.mappers;

import com.mybank.onboarding.adapters.inbound.requests.CreateUserRequest;
import com.mybank.onboarding.adapters.inbound.requests.UpdateUserRequest;
import com.mybank.onboarding.adapters.inbound.responses.UserDetailsResponse;
import com.mybank.onboarding.adapters.inbound.responses.UserListResponse;
import com.mybank.onboarding.domain.models.User;
import jakarta.validation.Valid;

public class UserRequestMapper {
    public static User toModel(CreateUserRequest request) {
        return User.builder()
                .birthDate(request.getBirthDate())
                .email(request.getEmail())
                .phone(request.getPhone())
                .document(request.getDocument())
                .fullName(request.getFullName())
                .password(request.getPassword())
                .build();
    }

    public static UserDetailsResponse toUserDetailsResponse(User model) {
        return UserDetailsResponse.builder()
                .id(model.getId())
                .bank(model.getBank())
                .agency(model.getAgency())
                .accountNumber(model.getAccountNumber())
                .phone(model.getPhone())
                .fullName(model.getFullName())
                .email(model.getEmail())
                .document(model.getDocument())
                .birthDate(model.getBirthDate())
                .build();
    }

    public static UserListResponse toUserListResponse(User user) {
        return UserListResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .accountNumber(user.getAccountNumber())
                .agency(user.getAgency())
                .bank(user.getBank())
                .build();
    }

    public static User updateToModel(UpdateUserRequest request) {
        return User.builder()
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
    }
}

package com.mybank.onboarding.application.mappers;

import com.mybank.onboarding.adapters.outbound.entities.UserEntity;
import com.mybank.onboarding.domain.models.User;

import java.util.UUID;

public class UserMapper {
    public static UserEntity toEntity(User model) {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .accountNumber(model.getAccountNumber())
                .agency(model.getAgency())
                .bank(model.getBank())
                .fullName(model.getFullName())
                .document(model.getDocument())
                .birthDate(model.getBirthDate())
                .email(model.getEmail())
                .phone(model.getPhone())
                .password(model.getPassword())
                .build();
    }

    public static User fromEntity(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .password(entity.getPassword())
                .document(entity.getDocument())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .accountNumber(entity.getAccountNumber())
                .bank(entity.getBank())
                .agency(entity.getAgency())
                .birthDate(entity.getBirthDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}

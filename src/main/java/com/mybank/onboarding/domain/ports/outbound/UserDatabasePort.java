package com.mybank.onboarding.domain.ports.outbound;

import com.mybank.onboarding.adapters.outbound.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDatabasePort {
    UserEntity createUser(UserEntity entity);

    Boolean existsByDocumentOrEmail(String document, String email);

    Optional<UserEntity> findById(UUID userId);

    Page<UserEntity> findAll(Pageable page);

    UserEntity updateUser(UserEntity entity);

    Boolean existsById(UUID userId);

    void deleteUser(UUID userId);
}

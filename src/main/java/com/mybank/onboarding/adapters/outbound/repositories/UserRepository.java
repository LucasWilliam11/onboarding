package com.mybank.onboarding.adapters.outbound.repositories;

import com.mybank.onboarding.adapters.outbound.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Boolean existsByDocumentOrEmail(String document, String email);
}

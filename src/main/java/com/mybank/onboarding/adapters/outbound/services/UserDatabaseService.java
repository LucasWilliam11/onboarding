package com.mybank.onboarding.adapters.outbound.services;

import com.mybank.onboarding.adapters.outbound.entities.UserEntity;
import com.mybank.onboarding.adapters.outbound.repositories.UserRepository;
import com.mybank.onboarding.domain.exceptions.DatabaseException;
import com.mybank.onboarding.domain.ports.outbound.UserDatabasePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDatabaseService implements UserDatabasePort {

    private final UserRepository repository;

    @Override
    @Transactional(rollbackOn = Exception.class, value = Transactional.TxType.REQUIRES_NEW)
    public UserEntity createUser(UserEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception ex) {
            log.error("Error to persist user in database.");
            throw new DatabaseException("Error to persist user in database.");
        }
    }

    @Override
    public Boolean existsByDocumentOrEmail(String document, String email) {
        try {
            return repository.existsByDocumentOrEmail(document, email);
        } catch (Exception ex) {
            log.error("Error to do query exists user in database.");
            throw new DatabaseException("Error to do query exists user in database.");
        }
    }

    @Override
    public Optional<UserEntity> findById(UUID userId) {
        try {
            return repository.findById(userId);
        } catch (Exception ex) {
            log.error("Error to do query find user by id in database.");
            throw new DatabaseException("Error to do query find user by id in database.");
        }
    }

    @Override
    public Page<UserEntity> findAll(Pageable page) {
        try {
            return repository.findAll(page);
        } catch (Exception ex) {
            log.error("Error to do query find all users in database.");
            throw new DatabaseException("Error to do query find all users in database.");
        }
    }

    @Override
    public UserEntity updateUser(UserEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception ex) {
            log.error("Error to do query update user in database.");
            throw new DatabaseException("Error to do query update user in database.");
        }
    }

    @Override
    public Boolean existsById(UUID userId) {
        try {
            return repository.existsById(userId);
        } catch (Exception ex) {
            log.error("Error to do query exists user by id in database.");
            throw new DatabaseException("Error to do query exists user by id in database.");
        }
    }

    @Override
    public void deleteUser(UUID userId) {
        try {
            repository.deleteById(userId);
        } catch (Exception ex) {
            log.error("Error to do query delete user by id in database.");
            throw new DatabaseException("Error to do query delete user by id in database.");
        }
    }
}

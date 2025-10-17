package com.mybank.onboarding.application.usecases;

import com.mybank.onboarding.application.mappers.UserMapper;
import com.mybank.onboarding.application.utils.AccountUtils;
import com.mybank.onboarding.application.utils.EncryptUtils;
import com.mybank.onboarding.domain.exceptions.EncryptException;
import com.mybank.onboarding.domain.exceptions.UserAlreadyExists;
import com.mybank.onboarding.domain.exceptions.UserNotFoundException;
import com.mybank.onboarding.domain.models.User;
import com.mybank.onboarding.domain.ports.inbound.UserUseCase;
import com.mybank.onboarding.domain.ports.outbound.UserDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static com.mybank.onboarding.application.mappers.UserMapper.fromEntity;
import static com.mybank.onboarding.application.mappers.UserMapper.toEntity;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {
    private static final String MY_BANK_NUMBER = "007";
    private static final String MY_BANK_AGENCY = "0007";

    private final UserDatabasePort userDatabasePort;

    @Override
    public User createUser(User model) {
        log.info("start create user.");

        this.fillAccountInfos(model);
        this.encryptFields(model);

        var entityFounded = userDatabasePort.existsByDocumentOrEmail(model.getDocument(), model.getEmail());

        if(entityFounded) {
            throw new UserAlreadyExists("User already exists.");
        }

        var entity = userDatabasePort.createUser(toEntity(model));

        log.info("User created successfully.");
        return fromEntity(entity);
    }

    @Override
    public User findUser(UUID userId) {
        log.info("Find user by id [{}]", userId);

        var userFounded = userDatabasePort.findById(userId);

        if(userFounded.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }

        var user = fromEntity(userFounded.get());
        this.decryptFields(user);

        return user;
    }

    @Override
    public Page<User> listUsers(Integer page, Integer size) {
        log.info("List users");

        if(page > 0) {
            page -= 1;
        }

        var usersFounded = userDatabasePort.findAll(PageRequest.of(page, size));

        var users = usersFounded.stream().map(UserMapper::fromEntity).toList();
        return new PageImpl<>(users, usersFounded.getPageable(), usersFounded.getTotalElements());
    }

    @Override
    public User updateUser(UUID userId, User user) {
        log.info("Start update user by id [{}]", userId);
        var userToUpdate = toEntity(user);

        var userFounded = userDatabasePort.findById(userId);

        if(userFounded.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }

        var userManaged = userFounded.get();
        if(Objects.nonNull(userToUpdate.getFullName())) {
            userManaged.setFullName(userToUpdate.getFullName());
        }

        if(Objects.nonNull(userToUpdate.getPhone())) {
            userManaged.setPhone(userToUpdate.getPhone());
        }

        if(Objects.nonNull(userToUpdate.getEmail())) {
            userManaged.setEmail(userToUpdate.getEmail());
        }

        var entity = userDatabasePort.updateUser(userManaged);
        var model = fromEntity(entity);
        this.decryptFields(model);

        log.info("User updated successfully.");

        return model;
    }

    @Override
    public void deleteUser(UUID userId) {
        log.info("Start delete user by id [{}]", userId);

        var entityFounded = userDatabasePort.existsById(userId);

        if(!entityFounded) {
            throw new UserNotFoundException("User not founded.");
        }

        userDatabasePort.deleteUser(userId);

        log.info("User deleted successfully.");
    }

    private void encryptFields(User model) {
        try {
            model.setDocument(EncryptUtils.encrypt(model.getDocument()));
            model.setPassword(EncryptUtils.encryptBcrypt(model.getPassword()));
        } catch (Exception e) {
            throw new EncryptException("Error to encrypt values.");
        }
    }

    private void decryptFields(User model) {
        try {
            model.setDocument(EncryptUtils.decrypt(model.getDocument()));
        } catch (Exception e) {
            throw new EncryptException("Error to encrypt values.");
        }
    }


    private void fillAccountInfos(User model) {
        model.setAccountNumber(AccountUtils.generateAccountNumber(8));
        model.setBank(MY_BANK_NUMBER);
        model.setAgency(MY_BANK_AGENCY);
    }

}

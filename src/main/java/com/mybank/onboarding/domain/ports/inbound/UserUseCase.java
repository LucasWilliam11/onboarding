package com.mybank.onboarding.domain.ports.inbound;

import com.mybank.onboarding.domain.models.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserUseCase {
    User createUser(User model);

    User findUser(UUID userId);

    Page<User> listUsers(Integer page, Integer size);

    User updateUser(UUID userId, User user);

    void deleteUser(UUID userId);
}

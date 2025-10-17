package com.mybank.onboarding.application.usecases;

import com.mybank.onboarding.adapters.outbound.entities.UserEntity;
import com.mybank.onboarding.application.utils.EncryptUtils;
import com.mybank.onboarding.domain.exceptions.UserAlreadyExists;
import com.mybank.onboarding.domain.exceptions.UserNotFoundException;
import com.mybank.onboarding.domain.models.User;
import com.mybank.onboarding.domain.ports.outbound.UserDatabasePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseImplTest {

    @Mock
    private UserDatabasePort userDatabasePort;

    @InjectMocks
    private UserUseCaseImpl userUseCase;

    private User user;
    private UserEntity entity;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(UUID.randomUUID())
                .fullName("John Doe")
                .document("123456789011")
                .email("johndoe@mail.com")
                .phone("1234567890")
                .password("123456")
                .build();

        entity = UserEntity.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .document(user.getDocument())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .build();
    }

    @Test
    public void shouldCreateUserSuccessfully() throws Exception {
        when(userDatabasePort.existsByDocumentOrEmail(EncryptUtils.encrypt("123456789011"), "johndoe@mail.com")).thenReturn(false);
        when(userDatabasePort.createUser(any())).thenReturn(entity);

        userUseCase.createUser(user);

        verify(userDatabasePort, times(1)).createUser(any());
    }

    @Test
    public void shouldCreateUserWithErrorAlreadyExists() throws Exception {
        when(userDatabasePort.existsByDocumentOrEmail(EncryptUtils.encrypt("123456789011"), "johndoe@mail.com")).thenReturn(true);

        assertThrows(UserAlreadyExists.class, () -> userUseCase.createUser(user));
    }

    @Test
    public void shouldDeleteUserSuccessfully() {
        when(userDatabasePort.existsById(user.getId())).thenReturn(true);

        userUseCase.deleteUser(user.getId());

        verify(userDatabasePort, times(1)).deleteUser(user.getId());
    }

    @Test
    public void shouldDeleteUserWithErrorNotFound() {
        when(userDatabasePort.existsById(user.getId())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userUseCase.deleteUser(user.getId()));
    }

    @Test
    public void shouldUpdateUserSuccessfully() throws Exception {
        when(userDatabasePort.findById(user.getId())).thenReturn(Optional.of(entity));

        entity.setDocument(EncryptUtils.encrypt("123456789012"));
        when(userDatabasePort.updateUser(any())).thenReturn(entity);

        userUseCase.updateUser(user.getId(), user);

        verify(userDatabasePort, times(1)).updateUser(any());
    }

    @Test
    public void shouldUpdateUserWithErrorNotFound() {
        when(userDatabasePort.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userUseCase.updateUser(user.getId(), user));
    }

    @Test
    public void shouldListUsersSuccessfully() {
        when(userDatabasePort.findAll(any())).thenReturn(new PageImpl<>(List.of(entity), PageRequest.of(1, 20), 1));

        var response = userUseCase.listUsers(1, 20);

        verify(userDatabasePort, times(1)).findAll(any());
        assertEquals(1, response.getContent().size());
    }

    @Test
    public void shouldFindUserByIdSuccessfully() throws Exception {
        when(userDatabasePort.findById(user.getId())).thenReturn(Optional.of(entity));

        entity.setDocument(EncryptUtils.encrypt("123456789012"));

        var response = userUseCase.findUser(user.getId());

        assertEquals(response.getId(), user.getId());
        verify(userDatabasePort, times(1)).findById(user.getId());
    }

    @Test
    public void shouldFindUserByIdWithErrorNotFound() {
        when(userDatabasePort.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userUseCase.findUser(user.getId()));
    }

}
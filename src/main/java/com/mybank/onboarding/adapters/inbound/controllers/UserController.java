package com.mybank.onboarding.adapters.inbound.controllers;

import com.mybank.onboarding.adapters.inbound.mappers.UserRequestMapper;
import com.mybank.onboarding.adapters.inbound.requests.CreateUserRequest;
import com.mybank.onboarding.adapters.inbound.requests.UpdateUserRequest;
import com.mybank.onboarding.adapters.inbound.responses.PageResponse;
import com.mybank.onboarding.adapters.inbound.responses.UserDetailsResponse;
import com.mybank.onboarding.domain.ports.inbound.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static com.mybank.onboarding.adapters.inbound.mappers.UserRequestMapper.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @Operation(summary = "API to create user", description = "Create a new user")
    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequest request) {
        var user = userUseCase.createUser(toModel(request));

        return ResponseEntity.created(URI.create(user.getId().toString())).build();
    }

    @Operation(summary = "API to find user by id", description = "Find user by id")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsResponse> findUser(@PathVariable("userId") UUID userId) {
        var user = userUseCase.findUser(userId);
        return ResponseEntity.ok(toUserDetailsResponse(user));
    }

    @Operation(summary = "API to list users", description = "List users")
    @GetMapping
    public ResponseEntity<PageResponse> listUsers(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        var usersPage = userUseCase.listUsers(pageNumber, pageSize);

        var users = usersPage.getContent().stream().map(UserRequestMapper::toUserListResponse).toList();

        var response = PageResponse.builder()
                .data(users)
                .size(usersPage.getSize())
                .page(usersPage.getPageable().getPageNumber() + 1)
                .last(usersPage.isLast())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "API to update user by id", description = "Update user by id")
    @PatchMapping("/{userId}")
    public ResponseEntity<UserDetailsResponse> updateUser(@PathVariable("userId") UUID userId, @Valid @RequestBody UpdateUserRequest request) {
        var user = userUseCase.updateUser(userId, updateToModel(request));


        return ResponseEntity.ok(toUserDetailsResponse(user));
    }

    @Operation(summary = "API to delete user by id", description = "Delete user by id")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") UUID userId) {
        userUseCase.deleteUser(userId);

        return ResponseEntity.ok().build();
    }
}

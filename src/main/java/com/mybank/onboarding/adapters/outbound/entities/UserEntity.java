package com.mybank.onboarding.adapters.outbound.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "tb_users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEntity {
    @Id
    private UUID id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "document", nullable = false)
    private String document;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "account_number", nullable = false)
    private String accountNumber;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "agency", nullable = false)
    private String agency;
    @Column(name = "bank", nullable = false)
    private String bank;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;
    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}

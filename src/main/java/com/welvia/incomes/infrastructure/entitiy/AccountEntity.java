package com.welvia.incomes.infrastructure.entitiy;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "accounts")
@Data
public class AccountEntity {

    @Id
    private Long id;

    @Column(name = "account_type_id")
    private Long accountTypeId;

    private String name;

    private BigDecimal balance;

    @Column(name = "is_main")
    private boolean main;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "is_active")
    private boolean active;
}
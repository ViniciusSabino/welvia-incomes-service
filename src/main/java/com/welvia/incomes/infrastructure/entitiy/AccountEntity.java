package com.welvia.incomes.infrastructure.entitiy;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Table("accounts")
@Data
public class AccountEntity {

    @Id
    private Long id;

    @Column("account_type_id")
    private Long accountTypeId;

    private String name;

    private BigDecimal balance;

    @Column("is_main")
    private boolean main;

    @CreatedDate
    @Column("created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private Instant updatedAt;

    @Column("is_active")
    private boolean active;
}
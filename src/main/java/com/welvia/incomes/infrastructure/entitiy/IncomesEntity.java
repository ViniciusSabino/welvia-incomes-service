package com.welvia.incomes.infrastructure.entitiy;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@Data
@Entity
@Table(name = "incomes")
public class IncomesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "income_type_id")
    private Long incomeTypeId;

    @Column(name = "income_status_id")
    private Long incomeStatusId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private Instant date;

    @Column(name = "received_at")
    private Instant receivedAt;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}

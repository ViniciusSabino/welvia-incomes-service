package com.welvia.incomes.infrastructure.entitiy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@Data
@Table(name = "incomes")
public class IncomesEntity {
    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    @Column("account_id")
    private Long accountId;

    @Column("income_type_id")
    private Long incomeTypeId;

    @Column("income_status_id")
    private Long incomeStatusId;

    @Column("amount")
    private BigDecimal amount;

    @Column("date")
    private Instant date;

    @Column("received_at")
    private Instant receivedAt;

    @Column("description")
    private String description;

    @CreatedDate
    @Column("created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private Instant updatedAt;
}

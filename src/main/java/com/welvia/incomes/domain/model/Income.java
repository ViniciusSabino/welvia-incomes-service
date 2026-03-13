package com.welvia.incomes.domain.model;

import com.welvia.incomes.domain.enums.IncomeStatuses;
import com.welvia.incomes.domain.enums.IncomeTypes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class Income {
    private Long id;
    private Long userId;
    private Account account;
    private IncomeTypes type;
    private IncomeStatuses status;
    private BigDecimal amount;
    private Instant date;
    private Instant receivedAt;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
}
package com.welvia.incomes.domain.model;

import com.welvia.incomes.domain.enums.IncomeTypes;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TypeSummary {
    IncomeTypes type;
    BigDecimal amount;
    Instant dateOfNextIncome;

}
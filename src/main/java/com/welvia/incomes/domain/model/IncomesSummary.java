package com.welvia.incomes.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class IncomesSummary {
    private BigDecimal totalExpected;
    private BigDecimal totalReceived;
    private List<TypeSummary> summariesByType;
}
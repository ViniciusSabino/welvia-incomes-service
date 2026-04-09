package com.welvia.incomes.domain.service;

import com.welvia.incomes.domain.enums.IncomeStatuses;
import com.welvia.incomes.domain.enums.IncomeTypes;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.model.TypeSummary;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SummaryDomainService {
    public List<Income> getReceivedIncomes(List<Income> incomes) {
        return incomes.stream().filter(income -> income.getStatus() == IncomeStatuses.RECEIVED).toList();
    }

    public BigDecimal getTotalExpected(List<Income> incomes) {
        return incomes.stream().map(Income::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalReceived(List<Income> receivedIncomes) {
        return receivedIncomes.stream().map(Income::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<TypeSummary> getSummariesPerType(List<Income> incomes) {
        List<TypeSummary> summaries = new ArrayList<>();

        for (IncomeTypes type : IncomeTypes.values()) {
            List<Income> incomesPerType = incomes.stream().filter(income -> income.getType().equals(type)).toList();
            BigDecimal amount = incomesPerType.stream().map(Income::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            Instant dateOfNextIncome = incomesPerType.stream().map(Income::getDate).filter(java.util.Objects::nonNull).min(Comparator.naturalOrder()).orElse(null);

            TypeSummary typeSummary = new TypeSummary();
            typeSummary.setType(type);
            typeSummary.setAmount(amount);
            typeSummary.setDateOfNextIncome(dateOfNextIncome);
            summaries.add(typeSummary);
        }

        return summaries;
    }
}
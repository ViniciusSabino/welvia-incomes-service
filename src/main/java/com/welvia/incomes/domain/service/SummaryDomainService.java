package com.welvia.incomes.domain.service;

import com.welvia.incomes.domain.enums.IncomeStatuses;
import com.welvia.incomes.domain.enums.IncomeTypes;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.model.TypeSummary;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SummaryDomainService {
    public List<Income> getExpectedIncomes(List<Income> incomes) {
        return incomes.stream().filter(i -> i.getStatus() == IncomeStatuses.EXPECTED).toList();
    }

    public List<Income> getReceivedIncomes(List<Income> incomes) {
        return incomes.stream().filter(i -> i.getStatus() == IncomeStatuses.RECEIVED).toList();
    }

    public BigDecimal getTotalExpected(List<Income> incomes) {
        return incomes.stream().reduce(new BigDecimal(0), (total, income) -> total.add(income.getAmount()), BigDecimal::add);
    }

    public BigDecimal getTotalReceived(List<Income> receivedIncomes) {
        return receivedIncomes.stream().reduce(new BigDecimal(0), (total, income) -> total.add(income.getAmount()), BigDecimal::add);
    }

    public List<TypeSummary> getSummariesPerType(List<Income> expectedIncomes) {
        List<IncomeTypes> allTypes = Arrays.stream(IncomeTypes.values()).toList();

        return allTypes.stream().map(type -> {
            TypeSummary typeSummary = new TypeSummary();
            List<Income> incomesPerType = expectedIncomes.stream().filter(income -> income.getType().equals(type)).toList();

            typeSummary.setType(type);
            typeSummary.setAmount(incomesPerType.stream().reduce(new BigDecimal(0), (total, income) -> total.add(income.getAmount()), BigDecimal::add));
            typeSummary.setDateOfNextIncome(!incomesPerType.isEmpty() ? incomesPerType.getFirst().getDate(): null);

            return typeSummary;
        }).toList();
    }
}
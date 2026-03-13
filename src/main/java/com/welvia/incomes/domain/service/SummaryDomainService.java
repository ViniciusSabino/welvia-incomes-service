package com.welvia.incomes.domain.service;

import com.welvia.incomes.domain.enums.IncomeStatuses;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.model.IncomesSummary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryDomainService {
    public IncomesSummary getSummaryByIncomes(List<Income> incomes) {
        List<Income> expectedIncomes = incomes.stream().filter(i -> i.getStatus() == IncomeStatuses.EXPECTED).toList();
        List<Income> receivedIncomes = incomes.stream().filter(i -> i.getStatus() == IncomeStatuses.RECEIVED).toList();

        IncomesSummary incomesSummary = new IncomesSummary();

        incomesSummary.setCountExpected(expectedIncomes.size());
        incomesSummary.setTotalExpected(expectedIncomes.stream().reduce(new BigDecimal(0), (total, income) -> total.add(income.getAmount()), BigDecimal::add));

        incomesSummary.setCountReceived(receivedIncomes.size());
        incomesSummary.setTotalReceived(receivedIncomes.stream().reduce(new BigDecimal(0), (total, income) -> total.add(income.getAmount()), BigDecimal::add));

        return incomesSummary;
    }
}
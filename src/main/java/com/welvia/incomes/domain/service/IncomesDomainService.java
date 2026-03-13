package com.welvia.incomes.domain.service;

import com.welvia.incomes.domain.enums.IncomeStatuses;
import com.welvia.incomes.domain.model.DateFilter;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.repository.IncomeRepository;
import com.welvia.incomes.domain.utils.DateUtil;

import java.util.List;

public class IncomesDomainService {
    private final IncomeRepository repository;

    public IncomesDomainService(IncomeRepository repository) {
        this.repository = repository;
    }

    public Income save(Income income) {
        income.setCreatedAt(DateUtil.now());
        income.setUpdatedAt(DateUtil.now());

        income.setStatus(DateUtil.now().isBefore(income.getDate()) ? IncomeStatuses.EXPECTED : IncomeStatuses.RECEIVED);
        income.setReceivedAt(income.getStatus().equals(IncomeStatuses.RECEIVED) ? income.getDate() : null);

        return repository.save(income);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public List<Income> listByDate(String month, String year) throws Exception {
        DateFilter dateFilter = new DateFilter(month, year);

        return repository.findByDate(dateFilter);
    }
}
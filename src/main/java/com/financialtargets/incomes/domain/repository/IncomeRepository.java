package com.financialtargets.incomes.domain.repository;

import com.financialtargets.incomes.domain.model.DateFilter;
import com.financialtargets.incomes.domain.model.Income;

import java.util.List;

public interface IncomeRepository {
    Income save(Income income);
    List<Income> findByDate(DateFilter dateFilter);
    void delete(Long id);
}
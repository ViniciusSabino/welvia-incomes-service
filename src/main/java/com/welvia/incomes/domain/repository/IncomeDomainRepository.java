package com.welvia.incomes.domain.repository;

import com.welvia.incomes.domain.model.DateFilter;
import com.welvia.incomes.domain.model.Income;

import java.util.List;

public interface IncomeDomainRepository {
    Income save(Income income);
    List<Income> findByDate(DateFilter dateFilter);
    void delete(Long id);
}
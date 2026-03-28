package com.welvia.incomes.domain.service;

import com.welvia.incomes.domain.enums.IncomeStatuses;
import com.welvia.incomes.domain.model.DateFilter;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.repository.IncomeDomainRepository;
import com.welvia.incomes.domain.utils.DateUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class IncomesDomainService {
    private final IncomeDomainRepository repository;

    public IncomesDomainService(IncomeDomainRepository repository) {
        this.repository = repository;
    }

    public Mono<Income> save(Income income) {
        income.setCreatedAt(DateUtil.now());
        income.setUpdatedAt(DateUtil.now());

        income.setStatus(DateUtil.now().isBefore(income.getDate()) ? IncomeStatuses.EXPECTED : IncomeStatuses.RECEIVED);
        income.setReceivedAt(income.getStatus().equals(IncomeStatuses.RECEIVED) ? income.getDate() : null);

        return repository.save(income);
    }

    public Mono<Void> delete(Long id) {
        return repository.delete(id);
    }

    public Flux<Income> listByDate(String month, String year) throws Exception {
        DateFilter dateFilter = new DateFilter(month, year);

        return repository.findByDate(dateFilter);
    }
}
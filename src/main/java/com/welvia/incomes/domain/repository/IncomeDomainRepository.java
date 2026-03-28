package com.welvia.incomes.domain.repository;

import com.welvia.incomes.domain.model.DateFilter;
import com.welvia.incomes.domain.model.Income;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IncomeDomainRepository {
    Mono<Income> save(Income income);
    Flux<Income> findByDate(DateFilter dateFilter);
    Mono<Void> delete(Long id);
}
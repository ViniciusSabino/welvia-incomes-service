package com.welvia.incomes.infrastructure.repository.impl;

import com.welvia.incomes.domain.model.DateFilter;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.repository.IncomeDomainRepository;
import com.welvia.incomes.infrastructure.entitiy.AccountEntity;
import com.welvia.incomes.infrastructure.entitiy.IncomesEntity;
import com.welvia.incomes.infrastructure.mapper.IncomeEntityMapper;
import com.welvia.incomes.infrastructure.repository.AccountR2dbcRepository;
import com.welvia.incomes.infrastructure.repository.IncomeR2dbcRepository;
import com.welvia.incomes.infrastructure.repository.IncomeStatusesR2dbcRepository;
import com.welvia.incomes.infrastructure.repository.IncomeTypesR2dbcRepository;
import com.welvia.incomes.infrastructure.repository.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class IncomeRepositoryImpl implements IncomeDomainRepository {
    private final R2dbcEntityTemplate template;
    private final IncomeR2dbcRepository repository;
    private final UserR2dbcRepository userR2dbcRepository;
    private final AccountR2dbcRepository accountRepository;
    private final IncomeTypesR2dbcRepository incomeTypesR2dbcRepository;
    private final IncomeStatusesR2dbcRepository incomeStatusesR2dbcRepository;
    private final IncomeEntityMapper mapper;

    @Override
    public Mono<Income> save(Income income) {
        IncomesEntity entity = mapper.toEntity(income);

        return Mono.zip(
                        userR2dbcRepository.findById(income.getUserId()),
                        accountRepository.findById(income.getAccount().getId()),
                        incomeTypesR2dbcRepository.findById(income.getType().getId()),
                        incomeStatusesR2dbcRepository.findById(income.getStatus().getId())
                )
                .flatMap(tuple -> {
                    AccountEntity account = tuple.getT2();

                    entity.setUserId(tuple.getT1().getId());
                    entity.setAccountId(account.getId());
                    entity.setIncomeTypeId(tuple.getT3().getId());
                    entity.setIncomeStatusId(tuple.getT4().getId());

                    return repository.save(entity)
                            .map(saved -> mapper.toModel(saved, account));
                });
    }

    @Override
    public Flux<Income> findByDate(DateFilter dateFilter) {
        Criteria criteria = Criteria
                .where("created_at").greaterThanOrEquals(dateFilter.getStartDate())
                .and("created_at").lessThanOrEquals(dateFilter.getEndDate());

        Query query = Query.query(criteria);

        return template.select(query, IncomesEntity.class).flatMap(income -> {
            Mono<AccountEntity> accountEntity = accountRepository.findById(income.getAccountId());

            return accountEntity.map(account -> mapper.toModel(income, account));
        });
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}
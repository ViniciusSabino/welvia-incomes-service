package com.welvia.incomes.infrastructure.repository.impl;

import com.welvia.incomes.domain.model.DateFilter;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.repository.IncomeRepository;
import com.welvia.incomes.infrastructure.entitiy.IncomesEntity;
import com.welvia.incomes.infrastructure.mapper.IncomeEntityMapper;
import com.welvia.incomes.infrastructure.repository.*;
import com.welvia.incomes.infrastructure.repository.specification.IncomeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IncomeRepositoryImpl implements IncomeRepository {
    private final IncomeJpaRepository repository;
    private final UserJpaRepository userJpaRepository;
    private final AccountJpaRepository accountJpaRepository;
    private final IncomeTypesJpaRepository incomeTypesJpaRepository;
    private final IncomeStatusesJpaRepository incomeStatusesJpaRepository;
    private final IncomeEntityMapper mapper;


    @Override
    public Income save(Income income) {
        IncomesEntity entity = mapper.toEntity(income);

        entity.setUser(userJpaRepository.getReferenceById(income.getUserId()));
        entity.setAccount(accountJpaRepository.getReferenceById(income.getAccount().getId()));
        entity.setIncomeType(incomeTypesJpaRepository.getReferenceById(income.getType().getId()));
        entity.setIncomeStatus(incomeStatusesJpaRepository.getReferenceById(income.getStatus().getId()));

        IncomesEntity saved = repository.save(entity);

        return mapper.toModel(saved);
    }

    @Override
    public List<Income> findByDate(DateFilter dateFilter) {
        List<IncomesEntity> incomes = repository.findAll(IncomeSpecification.byFilter(dateFilter)).stream().toList();

        return incomes.stream().map(mapper::toModel).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
package com.welvia.incomes.infrastructure.repository.impl;

import com.welvia.incomes.domain.model.DateFilter;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.repository.IncomeDomainRepository;
import com.welvia.incomes.infrastructure.entitiy.AccountEntity;
import com.welvia.incomes.infrastructure.entitiy.IncomesEntity;
import com.welvia.incomes.infrastructure.mapper.IncomeEntityMapper;
import com.welvia.incomes.infrastructure.repository.AccountJPARepository;
import com.welvia.incomes.infrastructure.repository.IncomeJPARepository;
import com.welvia.incomes.infrastructure.repository.IncomeStatusesJPARepository;
import com.welvia.incomes.infrastructure.repository.IncomeTypesJPARepository;
import com.welvia.incomes.infrastructure.repository.UserJPARepository;
import com.welvia.incomes.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IncomeRepositoryImpl implements IncomeDomainRepository {
    private final IncomeJPARepository repository;
    private final UserJPARepository userJPARepository;
    private final AccountJPARepository accountRepository;
    private final IncomeTypesJPARepository incomeTypesJPARepository;
    private final IncomeStatusesJPARepository incomeStatusesJPARepository;
    private final IncomeEntityMapper mapper;

    @Override
    public Income save(Income income) {
        IncomesEntity entity = mapper.toEntity(income);
        var user = userJPARepository.findById(income.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        AccountEntity account = accountRepository.findById(income.getAccount().getId()).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        var incomeType = incomeTypesJPARepository.findById(income.getType().getId()).orElseThrow(() -> new ResourceNotFoundException("Income type not found"));
        var incomeStatus = incomeStatusesJPARepository.findById(income.getStatus().getId()).orElseThrow(() -> new ResourceNotFoundException("Income status not found"));

        entity.setUserId(user.getId());
        entity.setAccountId(account.getId());
        entity.setIncomeTypeId(incomeType.getId());
        entity.setIncomeStatusId(incomeStatus.getId());

        IncomesEntity saved = repository.save(entity);
        return mapper.toModel(saved, account);
    }

    @Override
    public List<Income> findByDate(DateFilter dateFilter) {
        Specification<IncomesEntity> spec = (root, query, cb) -> cb.and(
                cb.greaterThanOrEqualTo(root.get("createdAt"), dateFilter.getStartDate()),
                cb.lessThanOrEqualTo(root.get("createdAt"), dateFilter.getEndDate())
        );

        return repository.findAll(spec).stream().map(income -> {
            AccountEntity accountEntity = accountRepository.findById(income.getAccountId()).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
            return mapper.toModel(income, accountEntity);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
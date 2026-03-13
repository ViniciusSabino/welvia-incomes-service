package com.welvia.incomes.infrastructure.mapper;

import com.welvia.incomes.domain.enums.IncomeStatuses;
import com.welvia.incomes.domain.enums.IncomeTypes;
import com.welvia.incomes.domain.model.Account;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.infrastructure.entitiy.IncomesEntity;
import org.springframework.stereotype.Component;

@Component
public class IncomeEntityMapper {

    public IncomesEntity toEntity(Income income) {
        IncomesEntity entity = new IncomesEntity();

        entity.setAmount(income.getAmount());
        entity.setDate(income.getDate());
        entity.setReceivedAt(income.getReceivedAt());
        entity.setCreatedAt(income.getCreatedAt());
        entity.setUpdatedAt(income.getUpdatedAt());
        entity.setDescription(income.getDescription());

        return entity;
    }

    public Income toModel(IncomesEntity entity) {
        return Income.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .account(Account.builder()
                        .id(entity.getAccount().getId())
                        .name(entity.getAccount().getName())
                        .build())
                .amount(entity.getAmount())
                .date(entity.getDate())
                .receivedAt(entity.getReceivedAt())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .status(IncomeStatuses.getStatusById(entity.getIncomeStatus().getId()))
                .type(IncomeTypes.getTypeById(entity.getIncomeType().getId()))
                .build();
    }
}
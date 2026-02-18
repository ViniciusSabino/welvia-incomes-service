package com.financialtargets.incomes.infrastructure.mapper;

import com.financialtargets.incomes.domain.enums.IncomeStatuses;
import com.financialtargets.incomes.domain.enums.IncomeTypes;
import com.financialtargets.incomes.domain.model.Income;
import com.financialtargets.incomes.infrastructure.entitiy.IncomesEntity;
import org.springframework.stereotype.Component;

@Component
public class IncomeEntityMapper {

    public IncomesEntity toEntity(Income income) {
        IncomesEntity entity = new IncomesEntity();

        entity.setAmount(income.getAmount());
        entity.setDate(income.getDate());
        entity.setCreatedAt(income.getCreatedAt());
        entity.setUpdatedAt(income.getUpdatedAt());
        entity.setDescription(income.getDescription());

        return entity;
    }

    public Income toModel(IncomesEntity entity) {
        return Income.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .accountName(entity.getUser().getName())
                .amount(entity.getAmount())
                .date(entity.getDate())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .status(IncomeStatuses.getStatusById(entity.getIncomeStatus().getId()))
                .type(IncomeTypes.getTypeById(entity.getIncomeType().getId()))
                .build();
    }
}
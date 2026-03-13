package com.welvia.incomes.application.mapper;

import com.welvia.incomes.application.dto.IncomeCreateDTO;
import com.welvia.incomes.domain.model.Account;
import com.welvia.incomes.domain.utils.DateUtil;
import com.welvia.incomes.domain.enums.IncomeStatuses;
import com.welvia.incomes.domain.enums.IncomeTypes;
import com.welvia.incomes.application.dto.IncomeResponseDTO;
import com.welvia.incomes.domain.model.Income;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
public class IncomesMapper {
    public Income toModel(IncomeCreateDTO incomeCreateDTO) {
        Instant incomeDate = DateUtil.createDateTime(incomeCreateDTO.date());

        return Income.builder()
                .userId(incomeCreateDTO.userId())
                .account(Account.builder()
                        .id(incomeCreateDTO.accountId())
                        .build())
                .amount(incomeCreateDTO.amount())
                .description(incomeCreateDTO.description())
                .date(incomeDate)
                .type(IncomeTypes.getTypeById(incomeCreateDTO.type()))
                .build();
    }

    public IncomeResponseDTO toResponse(Income income) {
        return IncomeResponseDTO.builder()
                .id(income.getId())
                .userId(income.getUserId())
                .accountName(income.getAccount().getName())
                .type(IncomeTypes.getLabelById(income.getType().getId()))
                .recurrence(IncomeTypes.getTypeById(income.getType().getId()).getBehavior().getLabel())
                .status(IncomeStatuses.getStatusById(income.getStatus().getId()).name())
                .amount(income.getAmount())
                .date(DateUtil.formatDate(income.getDate()))
                .receivedAt(Objects.isNull(income.getReceivedAt()) ? null : DateUtil.formatDate(income.getReceivedAt()))
                .description(income.getDescription())
                .createdAt(DateUtil.formatDateTime(income.getCreatedAt()))
                .updatedAt(DateUtil.formatDateTime(income.getUpdatedAt()))
                .build();
    }
}
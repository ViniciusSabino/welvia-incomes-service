package com.welvia.incomes.application.mapper;

import com.welvia.incomes.application.dto.IncomeTypeSummaryResponseDTO;
import com.welvia.incomes.application.dto.IncomesSummaryResponseDTO;
import com.welvia.incomes.domain.model.IncomesSummary;
import com.welvia.incomes.domain.utils.DateUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SummaryMapper {
    public IncomesSummaryResponseDTO toResponse(IncomesSummary incomesSummary) {
        List<IncomeTypeSummaryResponseDTO> summariesPerType = incomesSummary.getSummariesByType().stream().map(summary -> IncomeTypeSummaryResponseDTO
                .builder()
                .type(summary.getType().getPT_BR_LABEL())
                .amount(summary.getAmount())
                .dateOfNextIncome(!Objects.isNull(summary.getDateOfNextIncome()) ? DateUtil.formatDate(summary.getDateOfNextIncome()) : null)
                .build()).toList();

        return IncomesSummaryResponseDTO.builder()
                .totalExpected(incomesSummary.getTotalExpected())
                .totalReceived(incomesSummary.getTotalReceived())
                .summariesPerType(summariesPerType)
                .build();
    }
}
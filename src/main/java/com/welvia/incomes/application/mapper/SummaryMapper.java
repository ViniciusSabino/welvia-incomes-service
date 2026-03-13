package com.welvia.incomes.application.mapper;

import com.welvia.incomes.application.dto.IncomesSummaryResponseDTO;
import com.welvia.incomes.domain.model.IncomesSummary;
import org.springframework.stereotype.Component;

@Component
public class SummaryMapper {
    public IncomesSummaryResponseDTO toResponse(IncomesSummary incomesSummary) {
        return IncomesSummaryResponseDTO.builder()
                .countExpected(incomesSummary.getCountExpected())
                .totalExpected(incomesSummary.getTotalExpected())
                .countReceived(incomesSummary.getCountReceived())
                .totalReceived(incomesSummary.getTotalReceived())
                .build();
    }
}
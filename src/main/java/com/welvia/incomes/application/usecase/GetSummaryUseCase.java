package com.welvia.incomes.application.usecase;

import com.welvia.incomes.application.dto.IncomesSummaryResponseDTO;
import com.welvia.incomes.application.mapper.SummaryMapper;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.model.IncomesSummary;
import com.welvia.incomes.domain.service.IncomesDomainService;
import com.welvia.incomes.domain.service.SummaryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSummaryUseCase {
    private final IncomesDomainService incomesDomainService;
    private final SummaryDomainService summaryDomainService;
    private final SummaryMapper mapper;

    public IncomesSummaryResponseDTO byPeriod(String month, String year) throws Exception {
        log.trace("Get summary for the period {}/{}", month, year);

        List<Income> incomes = incomesDomainService.listByDate(month, year);

        IncomesSummary incomesSummary = summaryDomainService.getSummaryByIncomes(incomes);

        return mapper.toResponse(incomesSummary);
    }
}
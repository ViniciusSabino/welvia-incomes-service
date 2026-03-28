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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSummaryUseCase {
    private final IncomesDomainService incomesDomainService;
    private final SummaryDomainService service;
    private final SummaryMapper mapper;

    public Mono<IncomesSummaryResponseDTO> byPeriod(String month, String year) throws Exception {
        log.trace("Get summary for the period {}/{}", month, year);

        Flux<Income> incomes = incomesDomainService.listByDate(month, year).cache();
        Flux<Income> received = service.getReceivedIncomes(incomes);

        return Mono.zip(
                        service.getTotalExpected(incomes),
                        service.getTotalReceived(received),
                        service.getSummariesPerType(incomes).collectList()
                )
                .map(tuple -> {
                    IncomesSummary summary = new IncomesSummary();
                    summary.setTotalExpected(tuple.getT1());
                    summary.setTotalReceived(tuple.getT2());
                    summary.setSummariesByType(tuple.getT3());
                    return summary;
                })
                .map(mapper::toResponse);
    }
}
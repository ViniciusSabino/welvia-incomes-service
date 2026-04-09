package com.welvia.incomes.application.usecase;

import com.welvia.incomes.application.dto.IncomeResponseDTO;
import com.welvia.incomes.application.mapper.IncomesMapper;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.service.IncomesDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListingIncomeUseCase {
    private final IncomesDomainService service;
    private final IncomesMapper mapper;

    public List<IncomeResponseDTO> byPeriod(String month, String year) throws Exception {
        log.info("Listing incomes for the period {} to {}", month, year);

        List<Income> incomes = service.listByDate(month, year);

        log.info("Listed {} incomes", kv("incomeCount", incomes.size()));

        return incomes.stream().map(mapper::toResponse).toList();
    }
}
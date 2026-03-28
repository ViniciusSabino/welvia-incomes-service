package com.welvia.incomes.presentation.controller.impl;

import com.welvia.incomes.application.dto.IncomesSummaryResponseDTO;
import com.welvia.incomes.application.usecase.GetSummaryUseCase;
import com.welvia.incomes.presentation.controller.SummaryController;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class SummaryControllerImpl implements SummaryController {
    private final GetSummaryUseCase getSummaryUseCase;

    @GetMapping("/incomes")
    @Override
    public Mono<ResponseEntity<IncomesSummaryResponseDTO>> getSummary(@RequestParam @Valid @NonNull String month, @RequestParam @NonNull @Valid String year) throws Exception {
        log.trace("GET /summary/incomes - Get Summary incomes by month: {} and year: {}", month, year);

        return getSummaryUseCase.byPeriod(month,year).map(ResponseEntity.status(HttpStatus.OK)::body);
    }
}
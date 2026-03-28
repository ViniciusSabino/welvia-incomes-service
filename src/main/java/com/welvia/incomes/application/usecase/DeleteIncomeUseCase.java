package com.welvia.incomes.application.usecase;

import com.welvia.incomes.domain.service.IncomesDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteIncomeUseCase {
    private final IncomesDomainService service;

    public Mono<Void> delete(Long id) {
        return service.delete(id).doOnNext(Void -> log.info("Income delete successfully, incomeId: {}", id));
    }
}
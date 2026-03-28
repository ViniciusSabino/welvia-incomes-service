package com.welvia.incomes.presentation.controller.impl;

import com.welvia.incomes.application.usecase.CreateIncomeUseCase;
import com.welvia.incomes.application.usecase.DeleteIncomeUseCase;
import com.welvia.incomes.application.usecase.ListingIncomeUseCase;
import com.welvia.incomes.domain.exception.IncomeException;
import com.welvia.incomes.application.dto.IncomeCreateDTO;
import com.welvia.incomes.application.dto.IncomeResponseDTO;
import com.welvia.incomes.presentation.controller.IncomesController;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/incomes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class IncomesControllerImpl implements IncomesController {
    private final CreateIncomeUseCase createIncomeUseCase;
    private final DeleteIncomeUseCase deleteIncomeUseCase;
    private final ListingIncomeUseCase listingIncomeUseCase;

    @PostMapping
    @Override
    public Mono<ResponseEntity<IncomeResponseDTO>> create(@RequestBody @Valid IncomeCreateDTO incomeCreateDTO) throws IncomeException {
        log.trace("POST /incomes - Creating new income for user {}", incomeCreateDTO.userId());
        log.debug("Request body: {}", incomeCreateDTO);

        return createIncomeUseCase.create(incomeCreateDTO).map(ResponseEntity.status(HttpStatus.CREATED)::body);
    }

    @DeleteMapping("/{id}")
    @Override
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        log.trace("DELETE /incomes - Deleting a income for id {}", id);

        return deleteIncomeUseCase.delete(Long.valueOf(id)).map(Void -> ResponseEntity.status(HttpStatus.OK).build());
    }

    @GetMapping
    @Override
    public Mono<ResponseEntity<List<IncomeResponseDTO>>> listByMonth(@RequestParam @Valid @NonNull String month, @RequestParam @NonNull @Valid String year) throws Exception {
        log.info("GET /incomes - List Incomes by month: {} and year: {}", month, year);

        Flux<IncomeResponseDTO> incomes = listingIncomeUseCase.byPeriod(month, year);

        return incomes.collectList().map(ResponseEntity::ok);
    }
}
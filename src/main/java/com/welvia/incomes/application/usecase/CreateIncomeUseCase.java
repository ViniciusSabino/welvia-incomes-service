package com.welvia.incomes.application.usecase;

import com.welvia.incomes.application.dto.IncomeCreateDTO;
import com.welvia.incomes.application.dto.IncomeResponseDTO;
import com.welvia.incomes.domain.exception.IncomeException;
import com.welvia.incomes.application.mapper.IncomesMapper;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.service.IncomesDomainService;
import com.welvia.incomes.domain.validator.IncomeTypeValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateIncomeUseCase {
    private final IncomeTypeValidator incomeTypeValidator;
    private final IncomesDomainService service;
    private final IncomesMapper mapper;

    public Mono<IncomeResponseDTO> create(IncomeCreateDTO incomeCreateDTO) throws IncomeException {
        incomeTypeValidator.validate(incomeCreateDTO.type());

        Income income = mapper.toModel(incomeCreateDTO);

        Mono<Income> createdIncome = service.save(income);

        log.info("Income saved successfully, incomeId: {}", income.getId());

        return createdIncome.map(mapper::toResponse);
    }
}

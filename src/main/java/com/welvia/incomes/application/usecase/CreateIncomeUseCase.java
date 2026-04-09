package com.welvia.incomes.application.usecase;

import com.welvia.incomes.application.dto.IncomeCreateDTO;
import com.welvia.incomes.application.dto.IncomeResponseDTO;
import com.welvia.incomes.domain.enums.Event;
import com.welvia.incomes.domain.exception.IncomeException;
import com.welvia.incomes.application.mapper.IncomesMapper;
import com.welvia.incomes.domain.model.EventMessage;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.service.IncomesDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateIncomeUseCase {
    private final IncomesDomainService service;
    private final IncomesMapper mapper;

    public IncomeResponseDTO create(IncomeCreateDTO incomeCreateDTO) {
        Income income = mapper.toModel(incomeCreateDTO);

        Income savedIncome = service.save(income);

        log.info("Income saved successfully, incomeId: {}", savedIncome.getId());

        EventMessage eventMessage = new EventMessage(Event.INCOME_CREATED, savedIncome);

        service.sendEventNotification(eventMessage);

        return mapper.toResponse(savedIncome);
    }
}

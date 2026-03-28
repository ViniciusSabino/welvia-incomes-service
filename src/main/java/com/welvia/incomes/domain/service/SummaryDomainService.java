package com.welvia.incomes.domain.service;

import com.welvia.incomes.domain.enums.IncomeStatuses;
import com.welvia.incomes.domain.enums.IncomeTypes;
import com.welvia.incomes.domain.model.Income;
import com.welvia.incomes.domain.model.TypeSummary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

public class SummaryDomainService {
    public Flux<Income> getReceivedIncomes(Flux<Income> incomes) {
        return incomes.filter(income -> income.getStatus() == IncomeStatuses.RECEIVED);
    }

    public Mono<BigDecimal> getTotalExpected(Flux<Income> incomes) {
        return incomes.reduce(new BigDecimal(0), (total, income) -> total.add(income.getAmount()));
    }

    public Mono<BigDecimal> getTotalReceived(Flux<Income> receivedIncomes) {
        return receivedIncomes.reduce(new BigDecimal(0), (total, income) -> total.add(income.getAmount()));
    }

    public Flux<TypeSummary> getSummariesPerType(Flux<Income> incomes) {
        Flux<IncomeTypes> allTypes = Flux.just(IncomeTypes.values());

        return allTypes.flatMap(type -> {
            Flux<Income> incomesPerType = incomes.filter(income -> income.getType().equals(type));

            Mono<BigDecimal> amount = incomesPerType.reduce(BigDecimal.ZERO, (total, income) -> total.add(income.getAmount()));

            Mono<Optional<Instant>> dateOfNextIncome = incomesPerType.collectList().map(list -> !list.isEmpty() ? Optional.ofNullable(list.getFirst().getDate()) : Optional.<Instant>empty());

            return Mono.zip(amount, dateOfNextIncome).map(tuple -> {
                TypeSummary typeSummary = new TypeSummary();

                typeSummary.setType(type);
                typeSummary.setAmount(tuple.getT1());
                typeSummary.setDateOfNextIncome(tuple.getT2().orElse(null));

                return typeSummary;
            });
        });
    }
}
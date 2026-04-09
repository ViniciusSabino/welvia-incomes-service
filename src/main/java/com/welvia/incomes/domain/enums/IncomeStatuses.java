package com.welvia.incomes.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum IncomeStatuses {
    EXPECTED(1L),
    RECEIVED(2L),
    CANCELED(3L);

    private final Long id;

    IncomeStatuses(Long id) {
        this.id = id;
    }

    public static IncomeStatuses getStatusById(Long id) {
        Optional<IncomeStatuses> filtered = Arrays.stream(IncomeStatuses.values()).filter(i -> Objects.equals(i.getId(), id)).findFirst();

        return filtered.orElse(IncomeStatuses.RECEIVED);
    }
}
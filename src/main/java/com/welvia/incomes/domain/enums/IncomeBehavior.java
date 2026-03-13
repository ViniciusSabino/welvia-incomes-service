package com.welvia.incomes.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum IncomeBehavior {
    RECURRING("Recurring", "Recorrente"),
    ONE_TIME("One Time", "Uma Vez"),
    VARIABLE("Variable", "Variavél");

    private final String EN_LABEL;
    private final String PT_BR_LABEL;

    IncomeBehavior(String enLabel, String ptBRLabel) {
        this.EN_LABEL = enLabel;
        this.PT_BR_LABEL = ptBRLabel;
    }

    public String getLabel() {
        return this.PT_BR_LABEL;
    }
}

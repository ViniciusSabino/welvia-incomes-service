package com.welvia.incomes.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum IncomeTypes {
    FIXED(1L, IncomeBehavior.RECURRING, "Fixed Income", "Salário"),
    FREELANCE(2L, IncomeBehavior.VARIABLE, "Freelance", "Variável"),
    BUSINESS(3L, IncomeBehavior.VARIABLE, "Business Income", "Empresarial"),
    INVESTMENTS(4L, IncomeBehavior.RECURRING, "Investments", "Investimentos"),
    BONUS(5L, IncomeBehavior.ONE_TIME, "Bonus", "Bônus"),
    OTHER_INCOMES(6L, IncomeBehavior.VARIABLE, "Other", "Outros");

    private final Long id;
    private final IncomeBehavior behavior;
    private final String EN_LABEL;
    private final String PT_BR_LABEL;

    IncomeTypes(Long id, IncomeBehavior behavior, String enLabel, String ptBRLabel) {
        this.id = id;
        this.behavior = behavior;

        this.EN_LABEL = enLabel;
        this.PT_BR_LABEL = ptBRLabel;
    }

    public static IncomeTypes getTypeById(Long id) {
        Optional<IncomeTypes> filtered = Arrays.stream(IncomeTypes.values()).filter(i -> Objects.equals(i.getId(), id)).findFirst();

        return filtered.orElse(null);
    }

    public static String getLabelById(Long id) {
        Optional<IncomeTypes> filtered = Arrays.stream(IncomeTypes.values()).filter(i -> Objects.equals(i.getId(), id)).findFirst();

        return filtered.map(IncomeTypes::getPT_BR_LABEL).orElse(null);

    }
}
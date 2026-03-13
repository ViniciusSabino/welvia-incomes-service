package com.welvia.incomes.domain.validator;

import com.welvia.incomes.domain.enums.IncomeTypes;
import com.welvia.incomes.domain.exception.IncomeException;

import java.util.Arrays;
import java.util.Optional;

public class IncomeTypeValidator {
    public void validate(Long type) throws IncomeException {
        Optional<IncomeTypes> typeName = Arrays.stream(IncomeTypes.values()).filter(i -> IncomeTypes.getTypeById(type) == i).findFirst();

        if (typeName.isEmpty()) {
            throw new IncomeException("Incorrect type for input");
        }
    }
}
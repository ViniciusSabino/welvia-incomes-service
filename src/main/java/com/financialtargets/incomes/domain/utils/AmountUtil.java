package com.financialtargets.incomes.domain.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountUtil {
    public static String formatAmount(BigDecimal amount) {
        if (amount == null) return "R$ 0,00";

        BigDecimal decimal = amount.setScale(2, RoundingMode.HALF_UP);

        return "R$ " + decimal.toString().replace(".", ",");
    }
}

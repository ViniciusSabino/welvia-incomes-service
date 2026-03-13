package com.welvia.incomes.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public class IncomeTypeSummaryResponseDTO  extends RepresentationModel<IncomeTypeSummaryResponseDTO> implements Serializable {
    @JsonProperty("type")
    String type;

    @JsonProperty("amount")
    BigDecimal amount;

    @JsonProperty("dateOfNextIncome")
    String dateOfNextIncome;
}
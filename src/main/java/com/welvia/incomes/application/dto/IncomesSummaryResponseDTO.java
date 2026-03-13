package com.welvia.incomes.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class IncomesSummaryResponseDTO extends RepresentationModel<IncomesSummaryResponseDTO> implements Serializable {
    @JsonProperty("totalExpected")
    BigDecimal totalExpected;

    @JsonProperty("totalReceived")
    BigDecimal totalReceived;

    @JsonProperty("countExpected")
    Integer countExpected;

    @JsonProperty("countReceived")
    Integer countReceived;
}

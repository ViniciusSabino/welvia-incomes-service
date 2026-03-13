package com.financialtargets.incomes.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class IncomeResponseDTO extends RepresentationModel<IncomeResponseDTO> implements Serializable {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("userId")
        private Long userId;

        @JsonProperty("accountName")
        private String accountName;

        @JsonProperty("type")
        private String type;

        @JsonProperty("status")
        private String status;

        @JsonProperty("recurrence")
        private String recurrence;

        @JsonProperty("amount")
        private BigDecimal amount;

        @JsonProperty("date")
        private String date;

        @JsonProperty("receivedAt")
        private String receivedAt;

        @JsonProperty("description")
        private String description;

        @JsonProperty("createdAt")
        private String createdAt;

        @JsonProperty("updatedAt")
        private String updatedAt;
}

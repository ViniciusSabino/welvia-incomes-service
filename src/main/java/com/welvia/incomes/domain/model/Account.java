package com.welvia.incomes.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account {
    private Long id;
    private String name;
}

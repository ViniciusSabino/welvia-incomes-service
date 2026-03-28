package com.welvia.incomes.infrastructure.entitiy;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "income_types")
public class IncomeTypesEntity {
    @Id
    private Long id;

    @Column("type")
    private String type;
}

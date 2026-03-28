package com.welvia.incomes.infrastructure.entitiy;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("income_statuses")
public class IncomeStatusesEntity {
    @Id
    private Long id;

    @Column("status")
    private String status;
}
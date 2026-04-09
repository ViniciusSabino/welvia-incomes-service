package com.welvia.incomes.infrastructure.entitiy;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "income_types")
public class IncomeTypesEntity {
    @Id
    private Long id;

    @Column(name = "type")
    private String type;
}

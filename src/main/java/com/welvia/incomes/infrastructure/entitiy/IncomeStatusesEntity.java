package com.welvia.incomes.infrastructure.entitiy;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "income_statuses")
public class IncomeStatusesEntity {
    @Id
    private Long id;

    @Column(name = "status")
    private String status;
}
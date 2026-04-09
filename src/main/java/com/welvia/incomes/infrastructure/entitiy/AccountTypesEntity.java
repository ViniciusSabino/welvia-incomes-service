package com.welvia.incomes.infrastructure.entitiy;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "account_type")
public class AccountTypesEntity {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;
}
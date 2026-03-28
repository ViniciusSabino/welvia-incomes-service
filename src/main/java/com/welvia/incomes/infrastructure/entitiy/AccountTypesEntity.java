package com.welvia.incomes.infrastructure.entitiy;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("account_type")
public class AccountTypesEntity {
    @Id
    private Long id;

    @Column("name")
    private String name;
}
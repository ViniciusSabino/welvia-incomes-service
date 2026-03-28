package com.welvia.incomes.infrastructure.repository;

import com.welvia.incomes.infrastructure.entitiy.IncomeTypesEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeTypesR2dbcRepository extends R2dbcRepository<IncomeTypesEntity, Long> { }

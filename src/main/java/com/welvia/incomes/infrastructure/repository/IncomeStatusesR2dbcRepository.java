package com.welvia.incomes.infrastructure.repository;

import com.welvia.incomes.infrastructure.entitiy.IncomeStatusesEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeStatusesR2dbcRepository extends R2dbcRepository<IncomeStatusesEntity, Long> {
}

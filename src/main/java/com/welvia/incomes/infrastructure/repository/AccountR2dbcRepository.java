package com.welvia.incomes.infrastructure.repository;

import com.welvia.incomes.infrastructure.entitiy.AccountEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountR2dbcRepository extends R2dbcRepository<AccountEntity, Long> { }
package com.welvia.incomes.infrastructure.repository;

import com.welvia.incomes.infrastructure.entitiy.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJPARepository extends JpaRepository<AccountEntity, Long> { }
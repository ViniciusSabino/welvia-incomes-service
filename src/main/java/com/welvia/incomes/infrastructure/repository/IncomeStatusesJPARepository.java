package com.welvia.incomes.infrastructure.repository;

import com.welvia.incomes.infrastructure.entitiy.IncomeStatusesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeStatusesJPARepository extends JpaRepository<IncomeStatusesEntity, Long> {
}

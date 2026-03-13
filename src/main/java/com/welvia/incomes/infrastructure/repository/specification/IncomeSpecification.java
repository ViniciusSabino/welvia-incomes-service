package com.welvia.incomes.infrastructure.repository.specification;

import com.welvia.incomes.domain.model.DateFilter;
import com.welvia.incomes.infrastructure.entitiy.IncomesEntity;
import org.springframework.data.jpa.domain.Specification;

public class IncomeSpecification {
    public static Specification<IncomesEntity> byFilter(DateFilter filter) {
        return (root, query, cb) ->
                cb.between(root.get("date"), filter.getStartDate(), filter.getEndDate());
    }
}
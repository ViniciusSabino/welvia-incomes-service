package com.welvia.incomes.infrastructure.config.bean;

import com.welvia.incomes.domain.repository.IncomeDomainRepository;
import com.welvia.incomes.domain.service.IncomesDomainService;
import com.welvia.incomes.domain.service.SummaryDomainService;
import com.welvia.incomes.domain.validator.IncomeTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServicesConfig {
    @Bean
    public IncomesDomainService incomesDomainService(IncomeDomainRepository repository) {
        return new IncomesDomainService(repository);
    }

    @Bean
    public SummaryDomainService summaryDomainService() {
        return new SummaryDomainService();
    }

    @Bean
    public IncomeTypeValidator incomeTypeValidator() {
        return new IncomeTypeValidator();
    }
}

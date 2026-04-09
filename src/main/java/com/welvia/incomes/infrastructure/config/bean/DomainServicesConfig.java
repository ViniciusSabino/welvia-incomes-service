package com.welvia.incomes.infrastructure.config.bean;

import com.welvia.incomes.domain.clients.EventProducerDomainClient;
import com.welvia.incomes.domain.repository.IncomeDomainRepository;
import com.welvia.incomes.domain.service.IncomesDomainService;
import com.welvia.incomes.domain.service.SummaryDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServicesConfig {
    @Bean
    public IncomesDomainService incomesDomainService(IncomeDomainRepository repository, EventProducerDomainClient eventProducer) {
        return new IncomesDomainService(repository, eventProducer);
    }

    @Bean
    public SummaryDomainService summaryDomainService() {
        return new SummaryDomainService();
    }
}

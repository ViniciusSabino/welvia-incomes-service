package com.welvia.incomes.infrastructure.config;

import com.welvia.incomes.domain.constants.EventConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ApacheKafkaConfig {
    @Bean
    public NewTopic IncomeEventTopic() {
        return TopicBuilder.name(EventConstants.TOPIC_NAME)
                .partitions(6)
                .replicas(1)
                .build();
    }
}

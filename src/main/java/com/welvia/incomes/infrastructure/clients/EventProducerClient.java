package com.welvia.incomes.infrastructure.clients;

import com.welvia.incomes.domain.clients.EventProducerDomainClient;
import com.welvia.incomes.domain.model.EventMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducerClient implements EventProducerDomainClient {
    private final KafkaTemplate<String, EventMessage> kafkaTemplate;

    public EventProducerClient(KafkaTemplate<String, EventMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendEvent(String topic, EventMessage message) {
        kafkaTemplate.send(topic, String.valueOf(message.getData().getUserId()), message);
    }
}

package com.welvia.incomes.domain.clients;

import com.welvia.incomes.domain.model.EventMessage;

public interface EventProducerDomainClient {
    void sendEvent(String topic, EventMessage message);
}

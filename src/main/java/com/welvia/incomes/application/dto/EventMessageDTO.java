package com.welvia.incomes.application.dto;

import com.welvia.incomes.domain.enums.Event;

import java.time.Instant;

public record EventMessageDTO<A>(
        int eventId,
        Event eventType,
        Instant timestamp,
        String source,
        A data
) {
}

package com.welvia.incomes.domain.model;

import com.welvia.incomes.domain.constants.EventConstants;
import com.welvia.incomes.domain.enums.Event;
import com.welvia.incomes.domain.utils.DateUtil;
import lombok.Data;

import java.time.Instant;

@Data
public class EventMessage {
    private int eventId;
    private Event eventType;
    private String version;
    private Instant timestamp;
    private String source;
    private Income data;

    public EventMessage(Event eventType, Income data) {
        this.eventType = eventType;
        this.data = data;

        this.version = "v1";
        this.eventId = eventType.getId();
        this.timestamp = DateUtil.now();
        this.source = EventConstants.SOURCE_NAME;
    }
}

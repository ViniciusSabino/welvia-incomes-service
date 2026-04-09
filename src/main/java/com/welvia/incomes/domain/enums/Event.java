package com.welvia.incomes.domain.enums;

import lombok.Getter;

@Getter
public enum Event {
    INCOME_CREATED(1),
    INCOME_UPDATED(2);

    private final int id;

    Event(int id) {
        this.id = id;
    }
}

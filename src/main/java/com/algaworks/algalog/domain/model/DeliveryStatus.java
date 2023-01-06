package com.algaworks.algalog.domain.model;

import lombok.Getter;

@Getter
public enum DeliveryStatus {

    PENDING("pending"),
    FINISHED("finished"),
    CANCELED("canceld");

    private final String value;

    DeliveryStatus(String value) {
        this.value = value;
    }
}

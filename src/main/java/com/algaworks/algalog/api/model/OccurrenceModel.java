package com.algaworks.algalog.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OccurrenceModel {

    private DeliveryModel delivery;
    private String description;
    private OffsetDateTime registrationDate;
}

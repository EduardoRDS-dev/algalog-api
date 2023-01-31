package com.algaworks.algalog.api.model;

import com.algaworks.algalog.api.model.input.RemitteeInput;
import com.algaworks.algalog.domain.entity.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryModel {

    private Long id;
    private ClientModel clientModel;
    private RemitteeInput remitteeInput;
    private DeliveryStatus status;
    private BigDecimal tax;
    private OffsetDateTime requestDate;
    private OffsetDateTime deliveryDate;
}



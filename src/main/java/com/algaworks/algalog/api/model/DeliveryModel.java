package com.algaworks.algalog.api.model;

import com.algaworks.algalog.domain.entity.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryModel {

    private Long id;
    private ClientModel clientModel;
    private RemitteeModel remitteeModel;
    private DeliveryStatus status;
    private BigDecimal tax;
    private OffsetDateTime requestDate;
    private OffsetDateTime deliveryDate;
}



package com.algaworks.algalog.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DeliveryInput {


    @NotNull
    private Long clientId;

    @Valid
    @NotNull
    private RemitteeInput remitteeInput;

    @NotNull
    private BigDecimal tax;
}

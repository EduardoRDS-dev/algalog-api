package com.algaworks.algalog.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DeliveryInput {



    @NotNull
    @Positive
    private Long clientId;

    @Valid
    @NotNull
    private RemitteeInput remitteeInput;


    @NotNull
    @PositiveOrZero
    private BigDecimal tax;
}

package com.algaworks.algalog.domain.dto;

import com.algaworks.algalog.domain.model.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryDTO {

    private Long id;
    private ClientDTO clientDTO;
    private RemitteeDTO remitteeDTO;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeliveryStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal tax;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime requestDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime deliveryRequest;

    public DeliveryDTO(ClientDTO clientDTO, RemitteeDTO remitteeDTO) {
        this.clientDTO = clientDTO;
        this.remitteeDTO = remitteeDTO;
        this.status = DeliveryStatus.PENDING;
        this.tax = new BigDecimal(100);
        this.requestDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryDTO that = (DeliveryDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

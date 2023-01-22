package com.algaworks.algalog.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Client client;

    @Embedded
    private Remittee remittee;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;
    private BigDecimal tax;
    private Instant requestDate;
    private Instant deliveryDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(id, delivery.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

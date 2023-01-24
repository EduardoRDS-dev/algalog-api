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
@Table(name = "tb_delivery")
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

    @Column(name = "request_date")
    private Instant requestDate;

    @Column(name = "delivery_date")
    private Instant deliveryDate;

    public Delivery() {
    }

    public Delivery(Client client, Remittee remittee, DeliveryStatus status, BigDecimal tax, Instant requestDate) {
        this.client = client;
        this.remittee = remittee;
        this.status = status;
        this.tax = tax;
        this.requestDate = requestDate;
    }

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

package com.algaworks.algalog.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "delivery")
    private List<Occurrence> occurrences = new ArrayList<>();

    private BigDecimal tax;

    @Column(name = "request_date")
    private OffsetDateTime requestDate;

    @Column(name = "delivery_date")
    private OffsetDateTime deliveryDate;

    public Delivery() {
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

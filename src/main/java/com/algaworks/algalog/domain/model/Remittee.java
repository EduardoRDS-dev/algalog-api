package com.algaworks.algalog.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class Remittee {

    @Column(name = "remittee_name")
    private String name;

    @Column(name = "remittee_street")
    private String street;

    @Column(name = "remittee_number")
    private String number;

    @Column(name = "remittee_complement")
    private String complement;

    @Column(name = "remittee_district")
    private String district;
}

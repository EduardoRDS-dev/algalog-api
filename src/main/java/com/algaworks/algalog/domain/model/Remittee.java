package com.algaworks.algalog.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class Remittee {

    private String name;
    private String street;
    private String number;
    private String complement;
    private String district;
}

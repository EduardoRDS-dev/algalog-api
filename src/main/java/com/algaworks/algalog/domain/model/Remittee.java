package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.dto.RemitteeDTO;
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

    public Remittee(){}
    public Remittee (RemitteeDTO remitteeDTO) {
        this.name = remitteeDTO.getName();
        this.street = remitteeDTO.getStreet();
        this.number = remitteeDTO.getNumber();
        this.complement = remitteeDTO.getComplement();
        this.district = remitteeDTO.getDistrict();
    }
}

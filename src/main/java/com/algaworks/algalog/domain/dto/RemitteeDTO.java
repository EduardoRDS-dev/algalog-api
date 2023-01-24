package com.algaworks.algalog.domain.dto;

import com.algaworks.algalog.domain.model.Remittee;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemitteeDTO {

    @Size(max = 30)
    @NotBlank
    private String name;

    @Size(max = 255)
    @NotBlank
    private String street;

    @Size(max = 30)
    @NotBlank
    private String number;

    @Size(max = 30)
    @NotBlank
    private String district;

    @Size(max = 60)
    private String complement;

    public RemitteeDTO(Remittee remittee) {
        this.name = remittee.getName();
        this.street = remittee.getStreet();
        this.number = remittee.getNumber();
        this.complement = remittee.getComplement();
        this.district = remittee.getDistrict();
    }
}

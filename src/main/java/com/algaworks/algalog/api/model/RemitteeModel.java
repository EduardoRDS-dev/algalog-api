package com.algaworks.algalog.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemitteeModel {

    private String name;
    private String street;
    private String number;
    private String district;
    private String complement;
}

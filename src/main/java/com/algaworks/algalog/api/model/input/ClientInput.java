package com.algaworks.algalog.api.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInput {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(min = 9, max = 11)
    @NotBlank
    private String phone;
}

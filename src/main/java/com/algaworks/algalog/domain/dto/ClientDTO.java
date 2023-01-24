package com.algaworks.algalog.domain.dto;

import com.algaworks.algalog.domain.model.Client;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

    private Long id;

    @Size(max = 30)
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(min = 9, max = 11)
    @NotBlank
    private String phone;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.phone = client.getPhone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(id, clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
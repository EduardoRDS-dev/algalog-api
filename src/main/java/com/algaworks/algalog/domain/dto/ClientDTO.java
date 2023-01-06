package com.algaworks.algalog.domain.dto;

import com.algaworks.algalog.domain.model.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ClientDTO {

    private Long id;
    private String name;
    private String email;
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
        return Objects.equals(id, clientDTO.id) && Objects.equals(email, clientDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}

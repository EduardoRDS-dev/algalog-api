package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.ClientModel;
import com.algaworks.algalog.api.model.input.ClientInput;
import com.algaworks.algalog.domain.entity.Client;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class ClientMapper {

    private final ModelMapper modelMapper;

    public ClientModel toClientModel(Client client) {
        return modelMapper.map(client, ClientModel.class);
    }

    public Client toClient(ClientInput clientInput) {
        return modelMapper.map(clientInput, Client.class);
    }

    public List<ClientModel> toClientModelCollection(List<Client> clients) {
        List<ClientModel> result = new ArrayList<>();
        clients.forEach(client -> result.add(modelMapper.map(client, ClientModel.class)));
        return result;
    }
}

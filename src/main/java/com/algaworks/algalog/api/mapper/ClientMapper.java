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

    private ModelMapper modelMapper;

    public Client toClient(ClientInput clientInput) {
        return modelMapper.map(clientInput, Client.class);
    }

    public ClientModel toClientModel(Client client) {
        return modelMapper.map(client, ClientModel.class);
    }

    public List<ClientModel> toClientModelList(List<Client> clientList) {
        List<ClientModel> clientModelList = new ArrayList<>();
        clientList.forEach(client -> clientModelList.add(toClientModel(client)));
        return clientModelList;
    }
}

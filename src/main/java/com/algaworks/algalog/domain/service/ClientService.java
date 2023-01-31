package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.exceptions.BusinessException;
import com.algaworks.algalog.api.mapper.ClientMapper;
import com.algaworks.algalog.api.model.ClientModel;
import com.algaworks.algalog.api.model.input.ClientInput;
import com.algaworks.algalog.domain.entity.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


    public Optional<ClientModel> findById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toClientModel);
    }

    public Optional<Client> findByEmail(ClientInput clientInput) {
        return clientRepository.findByEmail(clientInput.getEmail());
    }

    public List<ClientModel> findAll() {
        return clientMapper.toClientModelCollection(clientRepository.findAll());
    }

    public ClientModel save(ClientInput clientInput) {

        findByEmail(clientInput).ifPresent(client -> {
            throw new BusinessException("e-mail in use!");
        });
        return clientMapper.toClientModel(clientRepository.save(clientMapper.toClient(clientInput)));
    }

    public ClientModel update(Long id, ClientInput clientInput) {

        Client clientFoundById = clientRepository.findById(id).orElseThrow(() -> new BusinessException("client not found!"));

        findByEmail(clientInput).ifPresent(clientFoundByEmail -> {
            if (!clientFoundByEmail.equals(clientFoundById)) {
                throw new BusinessException("e-mail in use!");
            }
        });
        return clientMapper.toClientModel(clientRepository.save(fieldUpdate(id, clientInput)));
    }

    public boolean deleteById(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Client fieldUpdate(Long id, ClientInput clientInput) {
        Client client = clientMapper.toClient(clientInput);
        client.setId(id);
        return client;
    }
}

package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.dto.ClientDTO;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<ClientDTO> findAll() {
        return repository.findAll().stream().map(ClientDTO::new).toList();
    }

    public Optional<ClientDTO> findById(Long id) {
        return repository.findById(id).map(ClientDTO::new).or(Optional::empty);
    }

    public ClientDTO save(Client client) {
        return new ClientDTO(client);
    }

    public Optional<ClientDTO> update(Long id, Client client) {

        if (findById(id).isPresent()) {
            client.setId(id);
            return Optional.of(save(client));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

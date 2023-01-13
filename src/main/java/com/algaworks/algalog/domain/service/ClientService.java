package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.dto.ClientDTO;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Optional<ClientDTO> findById(Long id) {
        return repository.findById(id).map(ClientDTO::new);
    }

    public void insert(ClientDTO clientDTO) {
        repository.save(new Client(clientDTO.getName(), clientDTO.getEmail(), clientDTO.getPhone()));
    }
}

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

    public Optional<ClientDTO> findById(Long id) {
        return repository.findById(id).map(ClientDTO::new);
    }

    public void insert(ClientDTO clientDTO) {
        repository.save(new Client(clientDTO.getName(), clientDTO.getEmail(), clientDTO.getPhone()));
    }

    public Optional<ClientDTO> update(Long id, ClientDTO clientDTO) {

        Optional<Client> optional = repository.findById(id);
        if (optional.isPresent()) {
            Client client = optional.get();
            client.setName(clientDTO.getName());
            client.setEmail(clientDTO.getEmail());
            client.setPhone(clientDTO.getPhone());
            repository.save(client);
            return Optional.of(new ClientDTO(client));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
        }
        return exist;
    }

    public List<ClientDTO> findAll() {
        return repository.findAll().stream().map(ClientDTO::new).toList();
    }
}

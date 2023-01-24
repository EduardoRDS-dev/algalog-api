package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.exceptions.BusinessException;
import com.algaworks.algalog.domain.dto.ClientDTO;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<ClientDTO> save(ClientDTO clientDTO) {

        Client newClient = new Client(clientDTO.getName(), clientDTO.getEmail(), clientDTO.getPhone());
        boolean match = repository.findByEmail(clientDTO.getEmail()).stream().anyMatch(client -> !client.equals(newClient));
        if (match) {
            throw new BusinessException("e-mail in use!");
        }
        return Optional.of(new ClientDTO(repository.save(newClient)));
    }

    @Transactional
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

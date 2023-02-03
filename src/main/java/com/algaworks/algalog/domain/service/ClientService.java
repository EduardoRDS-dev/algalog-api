package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.exceptions.BusinessException;
import com.algaworks.algalog.api.exceptions.ClientNotFoudException;
import com.algaworks.algalog.domain.entity.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> findByEmail(Client client) {
        return clientRepository.findByEmail(client.getEmail());
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client save(Client client) {
        findByEmail(client).ifPresent(clientExisting -> {
            throw new BusinessException("e-mail in use!");
        });
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(Client client) {
        Client clientFoundById = clientRepository.findById(client.getId()).orElseThrow(() -> new ClientNotFoudException("client not found!"));

        findByEmail(client).ifPresent(clientFoundByEmail -> {
            if (!clientFoundByEmail.equals(clientFoundById)) {
                throw new BusinessException("e-mail in use!");
            }
        });
        return clientRepository.save(client);
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

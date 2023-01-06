package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.dto.ClientDTO;
import com.algaworks.algalog.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<ClientDTO> findAll() {
        return repository.findAll().stream().map(ClientDTO::new).toList();
    }
}

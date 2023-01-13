package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.dto.ClientDTO;
import com.algaworks.algalog.domain.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        Optional<ClientDTO> optional = service.findById(id);
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientDTO clientDTO) {
        service.insert(clientDTO);
        return ResponseEntity.ok().build();
    }
}

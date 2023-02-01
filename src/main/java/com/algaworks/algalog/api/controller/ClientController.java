package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.mapper.ClientMapper;
import com.algaworks.algalog.api.model.ClientModel;
import com.algaworks.algalog.api.model.input.ClientInput;
import com.algaworks.algalog.domain.entity.Client;
import com.algaworks.algalog.domain.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping()
    public ResponseEntity<List<ClientModel>> findAll() {
        List<Client> clientList = clientService.findAll();
        return clientList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clientMapper.toClientModelList(clientList));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientModel> findById(@PathVariable Long clientId) {
        return clientService.findById(clientId)
                .map(client -> ResponseEntity.ok(clientMapper.toClientModel(client)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientModel save(@Valid @RequestBody ClientInput clientInput) {
        return clientMapper.toClientModel(clientService.save(clientMapper.toClient(clientInput)));
    }

    @PutMapping("/{clientId}")
    public ClientModel update(@PathVariable Long clientId, @Valid @RequestBody ClientInput clientInput) {
        Client client = clientMapper.toClient(clientInput);
        client.setId(clientId);
        return clientMapper.toClientModel(clientService.update(client));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {
        return clientService.deleteById(clientId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

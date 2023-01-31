package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.model.ClientModel;
import com.algaworks.algalog.api.model.input.ClientInput;
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

    @GetMapping()
    public ResponseEntity<List<ClientModel>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientModel> findById(@PathVariable Long clientId) {
        return clientService.findById(clientId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientModel save(@Valid @RequestBody ClientInput clientInput) {
        return clientService.save(clientInput);
    }

    @PutMapping("/{clientId}")
    public ClientModel update(@PathVariable Long clientId, @Valid @RequestBody ClientInput clientInput) {
        return clientService.update(clientId, clientInput);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {

        if (clientService.deleteById(clientId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

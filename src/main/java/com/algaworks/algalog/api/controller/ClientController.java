package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.dto.ClientDTO;
import com.algaworks.algalog.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

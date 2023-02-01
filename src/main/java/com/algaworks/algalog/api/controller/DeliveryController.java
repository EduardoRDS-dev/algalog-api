package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.mapper.DeliveryMapper;
import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.input.DeliveryInput;
import com.algaworks.algalog.domain.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/deliveries")
@AllArgsConstructor
public class DeliveryController {

    private DeliveryService requestDeliveryService;
    private DeliveryMapper deliveryMapper;

    @PostMapping
    public ResponseEntity<DeliveryModel> requestDelivery(@Valid @RequestBody DeliveryInput deliveryInput) {
        return ResponseEntity.ok(deliveryMapper.toDeliveryModel(requestDeliveryService.requestDelivery(deliveryInput)));
    }
}

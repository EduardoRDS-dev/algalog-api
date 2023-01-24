package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.dto.DeliveryDTO;
import com.algaworks.algalog.domain.service.DeliveryRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryRequest deliveryRequest;

    public DeliveryController(DeliveryRequest deliveryRequest) {
        this.deliveryRequest = deliveryRequest;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryDTO createDeliveryRequest(@Valid @RequestBody DeliveryDTO deliveryDTO) {
       return deliveryRequest.createDeliveryRequest(deliveryDTO);
    }
}

package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.mapper.DeliveryMapper;
import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.input.DeliveryInput;
import com.algaworks.algalog.domain.service.ResquestDeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
@AllArgsConstructor
public class DeliveryController {

    private ResquestDeliveryService requestDeliveryService;
    private DeliveryMapper deliveryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DeliveryModel requestDelivery(@Valid @RequestBody DeliveryInput deliveryInput) {
        return deliveryMapper.toDeliveryModel(requestDeliveryService.requestDelivery(deliveryInput));
    }
}

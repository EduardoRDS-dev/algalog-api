package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.mapper.DeliveryMapper;
import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.input.DeliveryInput;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import com.algaworks.algalog.domain.service.CancelDeliveryService;
import com.algaworks.algalog.domain.service.FinalizeDeliveryService;
import com.algaworks.algalog.domain.service.ResquestDeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
@AllArgsConstructor
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final ResquestDeliveryService requestDeliveryService;
    private final FinalizeDeliveryService finalizeDeliveryService;
    private final CancelDeliveryService cancelDeliveryService;
    private DeliveryMapper deliveryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DeliveryModel requestDelivery(@Valid @RequestBody DeliveryInput deliveryInput) {
        return deliveryMapper.toDeliveryModel(requestDeliveryService.requestDelivery(deliveryInput));
    }

    @PutMapping("/{deliveryId}/finalize")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fineshedDelivery(@PathVariable Long deliveryId) {
        finalizeDeliveryService.finalizeDelivery(deliveryId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{deliveryId}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelDelivery(@PathVariable Long deliveryId) {
        cancelDeliveryService.cancelDelivery(deliveryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DeliveryModel>> findAll() {

        List<DeliveryModel> deliveryModelList = deliveryMapper.toDeliveryModelList(deliveryRepository.findAll());
        return deliveryModelList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(deliveryModelList);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryModel> findById(@PathVariable Long deliveryId) {

        return deliveryRepository.findById(deliveryId)
                .map(delivery -> ResponseEntity.ok(deliveryMapper.toDeliveryModel(delivery)))
                .orElse(ResponseEntity.notFound().build());
    }
}

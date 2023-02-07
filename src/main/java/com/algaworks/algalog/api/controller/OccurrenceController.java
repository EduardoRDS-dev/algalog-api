package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.exceptions.EntityNotFoudException;
import com.algaworks.algalog.api.mapper.OccurrenceMapper;
import com.algaworks.algalog.api.model.OccurrenceModel;
import com.algaworks.algalog.api.model.input.OccurrenceInput;
import com.algaworks.algalog.domain.entity.Delivery;
import com.algaworks.algalog.domain.entity.Occurrence;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import com.algaworks.algalog.domain.service.AddOccurrenceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
@AllArgsConstructor
public class OccurrenceController {

    private final AddOccurrenceService addOccurrenceService;
    private final DeliveryRepository deliveryRepository;
    private final OccurrenceMapper occurrenceMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewOccurrence(@PathVariable Long deliveryId, @Valid @RequestBody OccurrenceInput occurrenceInput) {
        addOccurrenceService.addOccurrence(occurrenceInput.getDescription(), deliveryId);
    }

    @GetMapping()
    public ResponseEntity<List<OccurrenceModel>> listOccurrences(@PathVariable Long deliveryId) {

        List<Occurrence> occurrences = deliveryRepository.findById(deliveryId)
                .map(Delivery::getOccurrences)
                .orElseThrow(() -> new EntityNotFoudException("delivery not found!"));

        if (occurrences.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(occurrenceMapper.toOccurrenceModelList(occurrences));
    }
}

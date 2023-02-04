package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.mapper.OccurrenceMapper;
import com.algaworks.algalog.api.model.OccurrenceModel;
import com.algaworks.algalog.api.model.input.OccurrenceInput;
import com.algaworks.algalog.domain.entity.Occurrence;
import com.algaworks.algalog.domain.repository.OccurrenceRepository;
import com.algaworks.algalog.domain.service.AddOccurrenceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries/{deliveryId}/occurences")
@AllArgsConstructor
public class OccurrenceController {

    private final AddOccurrenceService addOccurrenceService;
    private final OccurrenceRepository occurrenceRepository;
    private final OccurrenceMapper occurrenceMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceModel addNewOccurrence(@PathVariable Long deliveryId, @Valid OccurrenceInput occurrenceInput) {

        Occurrence occurrence = addOccurrenceService.addOccurrence(occurrenceInput.getDescription(), deliveryId);
        return occurrenceMapper.toOccurrenceModel(occurrence);
    }

    @GetMapping
    public ResponseEntity<List<OccurrenceModel>> listOccurrences() {

        List<OccurrenceModel> occurrenceModelList = occurrenceMapper.toOccurrenceModelList(occurrenceRepository.findAll());
        return occurrenceModelList.isEmpty() ? ResponseEntity.ok(occurrenceModelList) : ResponseEntity.noContent().build();
    }
}

package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.exceptions.BusinessException;
import com.algaworks.algalog.api.exceptions.EntityNotFoudException;
import com.algaworks.algalog.domain.entity.Delivery;
import com.algaworks.algalog.domain.entity.DeliveryStatus;
import com.algaworks.algalog.domain.entity.Occurrence;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import com.algaworks.algalog.domain.repository.OccurrenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class AddOccurrenceService {

    private final OccurrenceRepository occurrenceRepository;
    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void addOccurrence(String description, Long deliveryId) {

        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() -> new EntityNotFoudException("delivery not found!"));

        if (!delivery.getStatus().equals(DeliveryStatus.PENDING)) {
            throw new BusinessException("delivery is " + delivery.getStatus().getValue() + "!");
        }
        Occurrence occurrence = new Occurrence();
        occurrence.setDelivery(delivery);
        occurrence.setDescription(description);
        occurrence.setRegistrationDate(OffsetDateTime.now());
        occurrenceRepository.save(occurrence);
    }
}

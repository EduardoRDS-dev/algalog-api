package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.mapper.DeliveryMapper;
import com.algaworks.algalog.api.model.input.DeliveryInput;
import com.algaworks.algalog.domain.entity.Delivery;
import com.algaworks.algalog.domain.entity.DeliveryStatus;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class DeliveryService {

    private DeliveryRepository deliveryRepository;
    private DeliveryMapper deliveryMapper;

    public Delivery requestDelivery(DeliveryInput deliveryInput) {

        Delivery newDelivery = deliveryMapper.toDelivery(deliveryInput);
        newDelivery.setStatus(DeliveryStatus.PENDING);
        newDelivery.setRequestDate(OffsetDateTime.now());
        return deliveryRepository.save(newDelivery);
    }
}

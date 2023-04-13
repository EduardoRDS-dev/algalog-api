package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.exceptions.EntityNotFoudException;
import com.algaworks.algalog.api.mapper.DeliveryMapper;
import com.algaworks.algalog.api.model.input.DeliveryInput;
import com.algaworks.algalog.domain.entity.Delivery;
import com.algaworks.algalog.domain.entity.DeliveryStatus;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class ResquestDeliveryService {

    private DeliveryRepository deliveryRepository;
    private ClientService clientService;
    private DeliveryMapper deliveryMapper;


    @Transactional
    public Delivery requestDelivery(DeliveryInput deliveryInput) {

        clientService.findById(deliveryInput.getClientId()).orElseThrow(() -> new EntityNotFoudException("client not found!"));
        Delivery newDelivery = deliveryMapper.toDelivery(deliveryInput);
        newDelivery.setStatus(DeliveryStatus.PENDING);
        newDelivery.setRequestDate(OffsetDateTime.now());
        return deliveryRepository.save(newDelivery);
    }
}

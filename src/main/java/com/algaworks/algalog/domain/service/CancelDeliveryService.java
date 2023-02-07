package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.exceptions.BusinessException;
import com.algaworks.algalog.api.exceptions.EntityNotFoudException;
import com.algaworks.algalog.domain.entity.Delivery;
import com.algaworks.algalog.domain.entity.DeliveryStatus;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CancelDeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void cancelDelivery(Long id) {

        Stream<Delivery> deliveryStream = Stream.<Delivery>builder()
                .add(deliveryRepository.findById(id).orElseThrow(() -> new EntityNotFoudException("delivery not found!"))).build();

        Delivery finishedDelivery = deliveryStream
                .filter(delivery -> delivery.getStatus().equals(DeliveryStatus.PENDING))
                .peek(delivery -> delivery.setStatus(DeliveryStatus.CANCELED))
                .reduce((delivery, x) -> delivery).orElseThrow(() -> new BusinessException("delivery cannot be canceled!"));

        deliveryRepository.save(finishedDelivery);
    }
}

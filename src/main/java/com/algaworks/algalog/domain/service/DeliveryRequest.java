package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.exceptions.BusinessException;
import com.algaworks.algalog.domain.dto.ClientDTO;
import com.algaworks.algalog.domain.dto.DeliveryDTO;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.Remittee;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DeliveryRequest {

    private final DeliveryRepository repository;
    private final ClientService clientService;

    public DeliveryRequest(DeliveryRepository repository, ClientService clientService) {
        this.repository = repository;
        this.clientService = clientService;
    }

    @Transactional
    public DeliveryDTO createDeliveryRequest(DeliveryDTO deliveryDTO) {

        Optional<Client> optionalClient = clientService.findById(deliveryDTO.getClientDTO().getId());
        Client clientChecked = optionalClient.orElseThrow(() -> (new BusinessException("client not found!")));

        repository.save(
                new Delivery(
                        clientChecked,
                        new Remittee(deliveryDTO.getRemitteeDTO()),
                        deliveryDTO.getStatus(),
                        deliveryDTO.getTax(),
                        deliveryDTO.getRequestDate()
                )
        );
        return new DeliveryDTO(new ClientDTO(clientChecked), deliveryDTO.getRemitteeDTO());
    }
}

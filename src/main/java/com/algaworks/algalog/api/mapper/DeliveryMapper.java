package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.ClientModel;
import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.input.DeliveryInput;
import com.algaworks.algalog.api.model.input.RemitteeInput;
import com.algaworks.algalog.domain.entity.Client;
import com.algaworks.algalog.domain.entity.Delivery;
import com.algaworks.algalog.domain.entity.Remittee;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeliveryMapper {

    private ModelMapper modelMapper;

    public Delivery toDelivery(DeliveryInput deliveryInput) {

        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(DeliveryInput.class, Delivery.class)
                .<Long>addMapping(DeliveryInput::getClientId, (delivery, srcClientId) -> delivery.getClient().setId(srcClientId))
                .<RemitteeInput>addMapping(DeliveryInput::getRemitteeInput, (delivery, srcRemitteeInput) -> delivery.setRemittee(modelMapper.map(RemitteeInput.class, Remittee.class)));

        return modelMapper.map(deliveryInput, Delivery.class);
    }

    public DeliveryModel toDeliveryModel(Delivery delivery) {

        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Delivery.class, DeliveryModel.class)
                .addMapping(Delivery::getId, DeliveryModel::setId)
                .<Client>addMapping(Delivery::getClient, (deliveryModel, srcClient) -> deliveryModel.setClientModel(modelMapper.map(Client.class, ClientModel.class)))
                .<Remittee>addMapping(Delivery::getRemittee, (deliveryModel, srcRemittee) -> deliveryModel.setRemitteeInput(modelMapper.map(Remittee.class, RemitteeInput.class)))
                .addMapping(Delivery::getId, DeliveryModel::setId);

        return modelMapper.map(delivery, DeliveryModel.class);
    }
}

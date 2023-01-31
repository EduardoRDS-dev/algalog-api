package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.input.DeliveryInput;
import com.algaworks.algalog.api.model.input.RemitteeInput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeliveryMapper {

    private final ModelMapper modelMapper;

    public DeliveryModel toDeliveryModel(DeliveryInput deliveryInput) {

        TypeMap<DeliveryInput, DeliveryModel> typeMap = modelMapper.createTypeMap(DeliveryInput.class, DeliveryModel.class);
        typeMap.addMapping(DeliveryInput::getClientId, (deliveryModel, valueId) -> deliveryModel.getClientModel().setId((Long) valueId));
        typeMap.addMapping(DeliveryInput::getRemitteeInput, (deliveryModel, remitteeInput) -> deliveryModel.setRemitteeInput((RemitteeInput) remitteeInput));
        return modelMapper.map(deliveryInput, DeliveryModel.class);
    }
}

package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.input.RemitteeInput;
import com.algaworks.algalog.domain.entity.Remittee;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RemitteeMapper {

    private final ModelMapper modelMapper;

    public Remittee toRemittee(RemitteeInput remitteeInput) {
        return modelMapper.map(remitteeInput, Remittee.class);
    }

    public RemitteeInput toRemitteeInput(Remittee remittee) {
        return modelMapper.map(remittee, RemitteeInput.class);
    }
}

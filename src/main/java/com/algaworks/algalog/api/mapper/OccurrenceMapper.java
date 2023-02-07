package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.OccurrenceModel;
import com.algaworks.algalog.domain.entity.Occurrence;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@AllArgsConstructor
public class OccurrenceMapper {

    private ModelMapper modelMapper;

    public OccurrenceModel toOccurrenceModel(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceModel.class);
    }

    public List<OccurrenceModel> toOccurrenceModelList(List<Occurrence> occurrences) {

        List<OccurrenceModel> occurrenceModelList = new LinkedList<>();
        occurrences.forEach(occurrence -> occurrenceModelList.add(toOccurrenceModel(occurrence)));
        return occurrenceModelList;
    }
}

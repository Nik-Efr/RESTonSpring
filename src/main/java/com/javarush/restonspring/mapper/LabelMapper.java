package com.javarush.restonspring.mapper;

import com.javarush.restonspring.dto.request.LabelRequestTo;
import com.javarush.restonspring.dto.response.LabelResponseTo;
import com.javarush.restonspring.model.Label;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LabelMapper {

    @Mapping(target = "id", ignore = true)
    Label toEntity(LabelRequestTo dto);

    LabelResponseTo toDto(Label entity);

}

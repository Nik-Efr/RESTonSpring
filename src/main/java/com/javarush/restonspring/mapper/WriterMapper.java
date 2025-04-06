package com.javarush.restonspring.mapper;

import com.javarush.restonspring.dto.request.WriterRequestTo;
import com.javarush.restonspring.dto.response.WriterResponseTo;
import com.javarush.restonspring.model.Writer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface WriterMapper {

    @Mapping(target = "id", ignore = true)
    Writer toEntity(WriterRequestTo dto);

    WriterResponseTo toDto(Writer entity);
}

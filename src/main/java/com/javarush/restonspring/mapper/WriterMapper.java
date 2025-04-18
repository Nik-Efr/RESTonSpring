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

    @Mapping(target = "id", source = "id")
    WriterResponseTo toDto(Writer writer);

    default Writer map(Long writerId) {
        if (writerId == null) return null;
        Writer writer = new Writer();
        writer.setId(writerId);
        return writer;
    }


}

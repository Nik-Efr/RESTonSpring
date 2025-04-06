package com.javarush.restonspring.mapper;

import com.javarush.restonspring.dto.request.TopicRequestTo;
import com.javarush.restonspring.dto.response.TopicResponseTo;
import com.javarush.restonspring.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    Topic toEntity(TopicRequestTo dto);

    TopicResponseTo toDto(Topic entity);
}

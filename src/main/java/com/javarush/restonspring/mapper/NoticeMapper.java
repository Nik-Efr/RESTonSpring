package com.javarush.restonspring.mapper;

import com.javarush.restonspring.dto.request.NoticeRequestTo;
import com.javarush.restonspring.dto.response.NoticeResponseTo;
import com.javarush.restonspring.model.Notice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoticeMapper {

    @Mapping(target = "id", ignore = true)
    Notice toEntity(NoticeRequestTo dto);

    NoticeResponseTo toDto(Notice entity);
}

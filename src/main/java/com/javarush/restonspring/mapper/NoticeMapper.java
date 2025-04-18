package com.javarush.restonspring.mapper;

import com.javarush.restonspring.dto.request.NoticeRequestTo;
import com.javarush.restonspring.dto.response.NoticeResponseTo;
import com.javarush.restonspring.model.Notice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = TopicMapper.class)
public interface NoticeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "topic", source = "topicId")
    Notice toEntity(NoticeRequestTo requestTo);

    @Mapping(target = "topicId", source = "topic.id")
    NoticeResponseTo toDto(Notice notice);
}

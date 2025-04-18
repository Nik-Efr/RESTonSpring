package com.javarush.restonspring.mapper;

import com.javarush.restonspring.dto.request.TopicRequestTo;
import com.javarush.restonspring.dto.response.TopicResponseTo;
import com.javarush.restonspring.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WriterMapper.class)
public interface TopicMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "writer", source = "writerId")
    Topic toEntity(TopicRequestTo requestTo);

    @Mapping(target = "writerId", source = "writer.id")
    TopicResponseTo toDto(Topic topic);

    default Topic map(Long topicId) {
        if (topicId == null) return null;
        Topic topic = new Topic();
        topic.setId(topicId);
        return topic;
    }

}

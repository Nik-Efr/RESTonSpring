package com.javarush.restonspring.repository.impl;

import com.javarush.restonspring.model.Topic;
import com.javarush.restonspring.repository.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TopicRepositoryImpl extends InMemoryRepository<Topic, Long> {

    @Override
    protected Long getId(Topic topic) {
        return topic.getId();
    }

    @Override
    protected void setId(Topic topic, Long id) {
        topic.setId(id);
    }
}

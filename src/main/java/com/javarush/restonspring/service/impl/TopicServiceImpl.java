package com.javarush.restonspring.service.impl;

import com.javarush.restonspring.exception.ResourceNotFoundException;
import com.javarush.restonspring.model.Topic;
import com.javarush.restonspring.repository.impl.TopicRepositoryImpl;
import com.javarush.restonspring.service.BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicServiceImpl implements BaseService<Topic> {

    private final TopicRepositoryImpl topicRepository;

    public TopicServiceImpl(TopicRepositoryImpl topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic create(Topic topic) {
        LocalDateTime now = LocalDateTime.now();
        topic.setCreated(now);
        topic.setModified(now);
        return topicRepository.save(topic);
    }

    @Override
    public Topic getById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic with ID " + id + " not found"));
    }

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic update(Long id, Topic topic) {
        Topic existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic with ID " + id + " not found"));

        topic.setId(id);
        topic.setCreated(existingTopic.getCreated());
        topic.setModified(LocalDateTime.now());
        return topicRepository.save(topic);
    }

    @Override
    public void deleteById(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new ResourceNotFoundException("Topic with ID " + id + " not found");
        }

        topicRepository.deleteById(id);
    }
}

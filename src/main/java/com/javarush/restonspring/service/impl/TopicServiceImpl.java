package com.javarush.restonspring.service.impl;

import com.javarush.restonspring.exception.DuplicateTitleException;
import com.javarush.restonspring.exception.ResourceNotFoundException;
import com.javarush.restonspring.model.Topic;
import com.javarush.restonspring.repository.LabelRepository;
import com.javarush.restonspring.repository.TopicRepository;
import com.javarush.restonspring.repository.WriterRepository;
import com.javarush.restonspring.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements BaseService<Topic> {

    private final TopicRepository topicRepository;
    private final WriterRepository writerRepository;
    private final LabelRepository labelRepository;
    public TopicServiceImpl(TopicRepository topicRepository, WriterRepository writerRepository, LabelRepository labelRepository) {
        this.topicRepository = topicRepository;
        this.writerRepository = writerRepository;
        this.labelRepository = labelRepository;
    }

    @Override
    public Topic create(Topic topic) {
        if (topic.getWriter() != null && topic.getWriter().getId() != null) {
            writerRepository.findById(topic.getWriter().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Writer with ID " + topic.getWriter().getId() + " not found"));
        }

        if (topicRepository.existsByTitle(topic.getTitle())) {
            throw new DuplicateTitleException(topic.getTitle());
        }

        LocalDateTime now = LocalDateTime.now();
        topic.setCreated(now);
        topic.setModified(now);

        return topicRepository.save(topic);
    }

    @Override
    @Transactional(readOnly = true)
    public Topic getById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic with ID " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Topic> getAll(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Override
    public Topic update(Long id, Topic topic) {
        Topic existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic with ID " + id + " not found"));

        if (topic.getWriter() != null && topic.getWriter().getId() != null) {
            writerRepository.findById(topic.getWriter().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Writer with ID " + topic.getWriter().getId() + " not found"));
        }

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

    @Transactional(readOnly = true)
    public Page<Topic> findByWriterId(Long writerId, Pageable pageable) {
        return topicRepository.findByWriterId(writerId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Topic> findByTitleContaining(String title, Pageable pageable) {
        return topicRepository.findByTitleContaining(title, pageable);
    }
}

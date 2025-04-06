package com.javarush.restonspring.controller;

import com.javarush.restonspring.dto.request.TopicRequestTo;
import com.javarush.restonspring.dto.response.TopicResponseTo;
import com.javarush.restonspring.mapper.TopicMapper;
import com.javarush.restonspring.model.Topic;
import com.javarush.restonspring.service.impl.TopicServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/topics")
public class TopicController {

    private final TopicServiceImpl topicService;
    private final TopicMapper topicMapper;

    public TopicController(TopicServiceImpl topicService, TopicMapper topicMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicResponseTo create(@Valid @RequestBody TopicRequestTo requestDto) {
        Topic topic = topicMapper.toEntity(requestDto);
        return topicMapper.toDto(topicService.create(topic));
    }

    @GetMapping("/{id}")
    public TopicResponseTo getById(@PathVariable Long id) {
        return topicMapper.toDto(topicService.getById(id));
    }

    @GetMapping
    public List<TopicResponseTo> getAll() {
        return topicService.getAll().stream()
                .map(topicMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public TopicResponseTo update(@PathVariable Long id, @Valid @RequestBody TopicRequestTo requestDto) {
        Topic updatedTopic = topicMapper.toEntity(requestDto);
        return topicMapper.toDto(topicService.update(id, updatedTopic));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        topicService.deleteById(id);
    }
}

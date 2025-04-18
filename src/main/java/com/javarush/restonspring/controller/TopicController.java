package com.javarush.restonspring.controller;

import com.javarush.restonspring.dto.request.TopicRequestTo;
import com.javarush.restonspring.dto.response.TopicResponseTo;
import com.javarush.restonspring.mapper.TopicMapper;
import com.javarush.restonspring.model.Topic;
import com.javarush.restonspring.service.impl.TopicServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TopicResponseTo create(@Valid @RequestBody TopicRequestTo requestTo) {
        Topic topic = topicMapper.toEntity(requestTo);
        return topicMapper.toDto(topicService.create(topic));
    }

    @GetMapping("/{id}")
    public TopicResponseTo getById(@PathVariable Long id) {
        return topicMapper.toDto(topicService.getById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TopicResponseTo> getAll() {
        return topicService.getAll().stream()
                .map(topicMapper::toDto)
                .toList();
    }

    @PutMapping("/{id}")
    public TopicResponseTo update(@PathVariable Long id, @Valid @RequestBody TopicRequestTo requestTo) {
        Topic topic = topicMapper.toEntity(requestTo);
        return topicMapper.toDto(topicService.update(id, topic));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        topicService.deleteById(id);
    }

    @GetMapping("/writer/{writerId}")
    public Page<TopicResponseTo> getByWriterId(@PathVariable Long writerId, Pageable pageable) {
        return topicService.findByWriterId(writerId, pageable).map(topicMapper::toDto);
    }

    @GetMapping("/search")
    public Page<TopicResponseTo> search(@RequestParam(required = false) String title, Pageable pageable) {
        if (title != null) {
            return topicService.findByTitleContaining(title, pageable).map(topicMapper::toDto);
        }
        return topicService.getAll(pageable).map(topicMapper::toDto);
    }
}

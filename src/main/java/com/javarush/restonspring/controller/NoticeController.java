package com.javarush.restonspring.controller;

import com.javarush.restonspring.dto.request.NoticeRequestTo;
import com.javarush.restonspring.dto.response.NoticeResponseTo;
import com.javarush.restonspring.mapper.NoticeMapper;
import com.javarush.restonspring.model.Notice;
import com.javarush.restonspring.service.impl.NoticeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/notices")
public class NoticeController {

    private final NoticeServiceImpl noticeService;
    private final NoticeMapper noticeMapper;

    public NoticeController(NoticeServiceImpl noticeService, NoticeMapper noticeMapper) {
        this.noticeService = noticeService;
        this.noticeMapper = noticeMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoticeResponseTo create(@Valid @RequestBody NoticeRequestTo requestTo) {
        Notice notice = noticeMapper.toEntity(requestTo);
        return noticeMapper.toDto(noticeService.create(notice));
    }

    @GetMapping("/{id}")
    public NoticeResponseTo getById(@PathVariable Long id) {
        return noticeMapper.toDto(noticeService.getById(id));
    }

    @GetMapping
    public Page<NoticeResponseTo> getAll(Pageable pageable) {
        return noticeService.getAll(pageable).map(noticeMapper::toDto);
    }

    @PutMapping("/{id}")
    public NoticeResponseTo update(@PathVariable Long id, @Valid @RequestBody NoticeRequestTo requestTo) {
        Notice notice = noticeMapper.toEntity(requestTo);
        return noticeMapper.toDto(noticeService.update(id, notice));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        noticeService.deleteById(id);
    }

    @GetMapping("/topic/{topicId}")
    public Page<NoticeResponseTo> getByTopicId(@PathVariable Long topicId, Pageable pageable) {
        return noticeService.findByTopicId(topicId, pageable).map(noticeMapper::toDto);
    }
}

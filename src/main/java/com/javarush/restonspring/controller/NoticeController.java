package com.javarush.restonspring.controller;

import com.javarush.restonspring.dto.request.NoticeRequestTo;
import com.javarush.restonspring.dto.response.NoticeResponseTo;
import com.javarush.restonspring.mapper.NoticeMapper;
import com.javarush.restonspring.model.Notice;
import com.javarush.restonspring.service.impl.NoticeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

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
    public NoticeResponseTo create(@Valid @RequestBody NoticeRequestTo requestDto) {
        Notice notice = noticeMapper.toEntity(requestDto);
        return noticeMapper.toDto(noticeService.create(notice));
    }

    @GetMapping("/{id}")
    public NoticeResponseTo getById(@PathVariable Long id) {
        return noticeMapper.toDto(noticeService.getById(id));
    }

    @GetMapping
    public List<NoticeResponseTo> getAll() {
        return noticeService.getAll().stream()
                .map(noticeMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public NoticeResponseTo update(@PathVariable Long id, @Valid @RequestBody NoticeRequestTo requestDto) {
        Notice updatedNotice = noticeMapper.toEntity(requestDto);
        return noticeMapper.toDto(noticeService.update(id, updatedNotice));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        noticeService.deleteById(id);
    }
}

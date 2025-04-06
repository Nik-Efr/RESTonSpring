package com.javarush.restonspring.controller;

import com.javarush.restonspring.dto.request.WriterRequestTo;
import com.javarush.restonspring.dto.response.WriterResponseTo;
import com.javarush.restonspring.mapper.WriterMapper;
import com.javarush.restonspring.model.Writer;
import com.javarush.restonspring.service.impl.WriterServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/writers")
public class WriterController {

    private final WriterServiceImpl writerService;
    private final WriterMapper writerMapper;

    public WriterController(WriterServiceImpl writerService, WriterMapper writerMapper) {
        this.writerService = writerService;
        this.writerMapper = writerMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WriterResponseTo create(@Valid @RequestBody WriterRequestTo requestDto) {
        Writer writer = writerMapper.toEntity(requestDto);
        return writerMapper.toDto(writerService.create(writer));
    }

    @GetMapping("/{id}")
    public WriterResponseTo getById(@PathVariable Long id) {
        return writerMapper.toDto(writerService.getById(id));
    }

    @GetMapping
    public List<WriterResponseTo> getAll() {
        return writerService.getAll().stream()
                .map(writerMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public WriterResponseTo update(@PathVariable Long id, @Valid @RequestBody WriterRequestTo requestDto) {
        Writer updatedWriter = writerMapper.toEntity(requestDto);
        return writerMapper.toDto(writerService.update(id, updatedWriter));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        writerService.deleteById(id);
    }
}

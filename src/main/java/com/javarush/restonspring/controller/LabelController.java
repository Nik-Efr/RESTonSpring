package com.javarush.restonspring.controller;

import com.javarush.restonspring.dto.request.LabelRequestTo;
import com.javarush.restonspring.dto.response.LabelResponseTo;
import com.javarush.restonspring.mapper.LabelMapper;
import com.javarush.restonspring.model.Label;
import com.javarush.restonspring.service.impl.LabelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/labels")
public class LabelController {

    private final LabelServiceImpl labelService;
    private final LabelMapper labelMapper;

    public LabelController(LabelServiceImpl labelService, LabelMapper labelMapper) {
        this.labelService = labelService;
        this.labelMapper = labelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelResponseTo create(@Valid @RequestBody LabelRequestTo requestDto) {
        Label label = labelMapper.toEntity(requestDto);
        return labelMapper.toDto(labelService.create(label));
    }

    @GetMapping("/{id}")
    public LabelResponseTo getById(@PathVariable Long id) {
        return labelMapper.toDto(labelService.getById(id));
    }

    @GetMapping
    public List<LabelResponseTo> getAll() {
        return labelService.getAll().stream()
                .map(labelMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public LabelResponseTo update(@PathVariable Long id, @Valid @RequestBody LabelRequestTo requestDto) {
        Label updatedLabel = labelMapper.toEntity(requestDto);
        return labelMapper.toDto(labelService.update(id, updatedLabel));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        labelService.deleteById(id);
    }
}

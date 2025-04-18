package com.javarush.restonspring.controller;

import com.javarush.restonspring.dto.request.LabelRequestTo;
import com.javarush.restonspring.dto.response.LabelResponseTo;
import com.javarush.restonspring.mapper.LabelMapper;
import com.javarush.restonspring.model.Label;
import com.javarush.restonspring.service.impl.LabelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public LabelResponseTo create(@Valid @RequestBody LabelRequestTo requestTo) {
        Label label = labelMapper.toEntity(requestTo);
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
                .toList();
    }

    @PutMapping("/{id}")
    public LabelResponseTo update(@PathVariable Long id, @Valid @RequestBody LabelRequestTo requestTo) {
        Label label = labelMapper.toEntity(requestTo);
        return labelMapper.toDto(labelService.update(id, label));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        labelService.deleteById(id);
    }

    @GetMapping("/search")
    public Page<LabelResponseTo> search(@RequestParam String name, Pageable pageable) {
        return labelService.findByNameContaining(name, pageable).map(labelMapper::toDto);
    }
}

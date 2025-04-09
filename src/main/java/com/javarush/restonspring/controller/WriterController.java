package com.javarush.restonspring.controller;

import com.javarush.restonspring.dto.request.WriterRequestTo;
import com.javarush.restonspring.dto.response.WriterResponseTo;
import com.javarush.restonspring.mapper.WriterMapper;
import com.javarush.restonspring.model.Writer;
import com.javarush.restonspring.service.impl.WriterServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public WriterResponseTo create(@Valid @RequestBody WriterRequestTo requestTo) {
        Writer writer = writerMapper.toEntity(requestTo);
        return writerMapper.toDto(writerService.create(writer));
    }

    @GetMapping("/{id}")
    public WriterResponseTo getById(@PathVariable Long id) {
        return writerMapper.toDto(writerService.getById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<WriterResponseTo> getAll(Pageable pageable) {
        return writerService.getAll(pageable).map(writerMapper::toDto);
    }

    @PutMapping("/{id}")
    public WriterResponseTo update(@PathVariable Long id, @Valid @RequestBody WriterRequestTo requestTo) {
        Writer writer = writerMapper.toEntity(requestTo);
        return writerMapper.toDto(writerService.update(id, writer));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        writerService.deleteById(id);
    }

    @GetMapping("/search")
    public Page<WriterResponseTo> search(@RequestParam(required = false) String login,
                                         @RequestParam(required = false) String name,
                                         Pageable pageable) {
        if (login != null) {
            return writerService.findByLoginContaining(login, pageable).map(writerMapper::toDto);
        } else if (name != null) {
            return writerService.findByName(name, pageable).map(writerMapper::toDto);
        }
        return writerService.getAll(pageable).map(writerMapper::toDto);
    }
}

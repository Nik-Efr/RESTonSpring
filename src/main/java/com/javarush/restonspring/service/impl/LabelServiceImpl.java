package com.javarush.restonspring.service.impl;

import com.javarush.restonspring.exception.ResourceNotFoundException;
import com.javarush.restonspring.model.Label;
import com.javarush.restonspring.repository.LabelRepository;
import com.javarush.restonspring.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LabelServiceImpl implements BaseService<Label> {

    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label create(Label label) {
        return labelRepository.save(label);
    }

    @Override
    @Transactional(readOnly = true)
    public Label getById(Long id) {
        return labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Label with ID " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Label> getAll() {
        return labelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Label> getAll(Pageable pageable) {
        return labelRepository.findAll(pageable);
    }

    @Override
    public Label update(Long id, Label label) {
        if (!labelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Label with ID " + id + " not found");
        }
        label.setId(id);
        return labelRepository.save(label);
    }

    @Override
    public void deleteById(Long id) {
        if (!labelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Label with ID " + id + " not found");
        }
        labelRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Label> findByNameContaining(String name, Pageable pageable) {
        return labelRepository.findByNameContaining(name, pageable);
    }
}

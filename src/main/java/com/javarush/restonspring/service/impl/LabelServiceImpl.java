package com.javarush.restonspring.service.impl;

import com.javarush.restonspring.exception.ResourceNotFoundException;
import com.javarush.restonspring.model.Label;
import com.javarush.restonspring.repository.impl.LabelRepositoryImpl;
import com.javarush.restonspring.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements BaseService<Label> {

    private final LabelRepositoryImpl labelRepository;

    public LabelServiceImpl(LabelRepositoryImpl labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label create(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public Label getById(Long id) {
        return labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Label with ID " + id + " not found"));
    }

    @Override
    public List<Label> getAll() {
        return labelRepository.findAll();
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
}

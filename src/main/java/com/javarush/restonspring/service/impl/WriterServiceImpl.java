package com.javarush.restonspring.service.impl;

import com.javarush.restonspring.exception.ResourceNotFoundException;
import com.javarush.restonspring.model.Writer;
import com.javarush.restonspring.repository.impl.WriterRepositoryImpl;
import com.javarush.restonspring.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements BaseService<Writer> {

    private final WriterRepositoryImpl writerRepository;

    public WriterServiceImpl(WriterRepositoryImpl writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public Writer create(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public Writer getById(Long id) {
        return writerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Writer with ID " + id + " not found"));
    }

    @Override
    public List<Writer> getAll() {
        return writerRepository.findAll();
    }

    @Override
    public Writer update(Long id, Writer writer) {
        if (!writerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Writer with ID " + id + " not found");
        }
        writer.setId(id);
        return writerRepository.save(writer);
    }

    @Override
    public void deleteById(Long id) {
        if (!writerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Writer with ID " + id + " not found");
        }
        writerRepository.deleteById(id);
    }
}

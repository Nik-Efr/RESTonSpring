package com.javarush.restonspring.service.impl;

import com.javarush.restonspring.exception.DuplicateLoginException;
import com.javarush.restonspring.exception.ResourceNotFoundException;
import com.javarush.restonspring.model.Writer;
import com.javarush.restonspring.repository.WriterRepository;
import com.javarush.restonspring.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WriterServiceImpl implements BaseService<Writer> {

    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public Writer create(Writer writer) {
        if (writerRepository.existsByLogin(writer.getLogin())) {
            throw new DuplicateLoginException(writer.getLogin());
        }
        return writerRepository.save(writer);
    }


    @Override
    @Transactional(readOnly = true)
    public Writer getById(Long id) {
        return writerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Writer with ID " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Writer> getAll() {
        return writerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Writer> getAll(Pageable pageable) {
        return writerRepository.findAll(pageable);
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


    @Transactional(readOnly = true)
    public Page<Writer> findByLoginContaining(String login, Pageable pageable) {
        return writerRepository.findByLoginContaining(login, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Writer> findByName(String name, Pageable pageable) {
        return writerRepository.findByFirstnameContainingOrLastnameContaining(name, name, pageable);
    }
}

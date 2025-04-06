package com.javarush.restonspring.repository.impl;

import com.javarush.restonspring.model.Writer;
import com.javarush.restonspring.repository.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class WriterRepositoryImpl extends InMemoryRepository<Writer, Long> {

    @Override
    protected Long getId(Writer writer) {
        return writer.getId();
    }

    @Override
    protected void setId(Writer writer, Long id) {
        writer.setId(id);
    }
}

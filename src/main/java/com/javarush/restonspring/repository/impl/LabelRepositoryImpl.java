package com.javarush.restonspring.repository.impl;

import com.javarush.restonspring.model.Label;
import com.javarush.restonspring.repository.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LabelRepositoryImpl extends InMemoryRepository<Label, Long> {

    @Override
    protected Long getId(Label label) {
        return label.getId();
    }

    @Override
    protected void setId(Label label, Long id) {
        label.setId(id);
    }
}

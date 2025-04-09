package com.javarush.restonspring.repository;

import com.javarush.restonspring.model.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends BaseRepository<Label, Long> {
    Page<Label> findByNameContaining(String name, Pageable pageable);

    Page<Label> findByTopicsId(Long topicId, Pageable pageable);
}

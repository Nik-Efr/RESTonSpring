package com.javarush.restonspring.repository;

import com.javarush.restonspring.model.Topic;
import com.javarush.restonspring.model.Writer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TopicRepository extends BaseRepository<Topic, Long> {
    Page<Topic> findByWriter(Writer writer, Pageable pageable);

    Page<Topic> findByWriterId(Long writerId, Pageable pageable);

    Page<Topic> findByTitleContaining(String title, Pageable pageable);

    Page<Topic> findByContentContaining(String content, Pageable pageable);

    Page<Topic> findByCreatedBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    boolean existsByTitle(String title);
}

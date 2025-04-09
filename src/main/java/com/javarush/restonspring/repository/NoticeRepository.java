package com.javarush.restonspring.repository;

import com.javarush.restonspring.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends BaseRepository<Notice, Long> {
    Page<Notice> findByTopicId(Long topicId, Pageable pageable);

    Page<Notice> findByContentContaining(String content, Pageable pageable);
}

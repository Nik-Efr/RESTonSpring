package com.javarush.restonspring.repository.impl;

import com.javarush.restonspring.model.Notice;
import com.javarush.restonspring.repository.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeRepositoryImpl extends InMemoryRepository<Notice, Long> {

    @Override
    protected Long getId(Notice notice) {
        return notice.getId();
    }

    @Override
    protected void setId(Notice notice, Long id) {
        notice.setId(id);
    }
}

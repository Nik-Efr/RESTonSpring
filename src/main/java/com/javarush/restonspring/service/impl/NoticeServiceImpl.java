package com.javarush.restonspring.service.impl;

import com.javarush.restonspring.exception.ResourceNotFoundException;
import com.javarush.restonspring.model.Notice;
import com.javarush.restonspring.repository.impl.NoticeRepositoryImpl;
import com.javarush.restonspring.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements BaseService<Notice> {

    private final NoticeRepositoryImpl noticeRepository;

    public NoticeServiceImpl(NoticeRepositoryImpl noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Notice create(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public Notice getById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notice with ID " + id + " not found"));
    }

    @Override
    public List<Notice> getAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Notice update(Long id, Notice notice) {
        if (!noticeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notice with ID " + id + " not found");
        }
        notice.setId(id);
        return noticeRepository.save(notice);
    }

    @Override
    public void deleteById(Long id) {
        if (!noticeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notice with ID " + id + " not found");
        }
        noticeRepository.deleteById(id);
    }
}

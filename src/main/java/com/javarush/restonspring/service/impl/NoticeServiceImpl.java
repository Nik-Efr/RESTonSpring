package com.javarush.restonspring.service.impl;

import com.javarush.restonspring.exception.ResourceNotFoundException;
import com.javarush.restonspring.model.Notice;
import com.javarush.restonspring.repository.NoticeRepository;
import com.javarush.restonspring.repository.TopicRepository;
import com.javarush.restonspring.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements BaseService<Notice> {

    private final NoticeRepository noticeRepository;
    private final TopicRepository topicRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository, TopicRepository topicRepository) {
        this.noticeRepository = noticeRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public Notice create(Notice notice) {
        if (notice.getTopic() != null && notice.getTopic().getId() != null) {
            topicRepository.findById(notice.getTopic().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Topic with ID " + notice.getTopic().getId() + " not found"));
        }
        return noticeRepository.save(notice);
    }

    @Override
    @Transactional(readOnly = true)
    public Notice getById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notice with ID " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notice> getAll() {
        return noticeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Notice> getAll(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }

    @Override
    public Notice update(Long id, Notice notice) {
        if (!noticeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notice with ID " + id + " not found");
        }

        if (notice.getTopic() != null && notice.getTopic().getId() != null) {
            topicRepository.findById(notice.getTopic().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Topic with ID " + notice.getTopic().getId() + " not found"));
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

    @Transactional(readOnly = true)
    public Page<Notice> findByTopicId(Long topicId, Pageable pageable) {
        return noticeRepository.findByTopicId(topicId, pageable);
    }
}

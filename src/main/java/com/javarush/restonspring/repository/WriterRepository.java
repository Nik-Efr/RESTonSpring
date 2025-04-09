package com.javarush.restonspring.repository;

import com.javarush.restonspring.model.Writer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends BaseRepository<Writer, Long> {
    Page<Writer> findByLoginContaining(String login, Pageable pageable);

    Page<Writer> findByFirstnameContainingOrLastnameContaining(String firstname, String lastname, Pageable pageable);
}

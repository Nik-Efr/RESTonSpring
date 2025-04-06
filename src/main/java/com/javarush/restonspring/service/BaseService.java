package com.javarush.restonspring.service;

import java.util.List;

public interface BaseService<T> {
    T create(T entity);

    T getById(Long id);

    List<T> getAll();

    T update(Long id, T entity);

    void deleteById(Long id);
}

package com.javarush.restonspring.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class InMemoryRepository<T, ID> implements Repository<T, ID> {

    protected final Map<ID, T> storage = new ConcurrentHashMap<>();
    protected final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public T save(T entity) {
        ID id = getId(entity);
        if (id == null) {
            id = (ID) generateId();
            setId(entity, id);
        }
        storage.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(ID id) {
        storage.remove(id);
    }

    @Override
    public boolean existsById(ID id) {
        return storage.containsKey(id);
    }

    protected abstract ID getId(T entity);

    protected abstract void setId(T entity, ID id);

    protected Long generateId() {
        return idGenerator.getAndIncrement();
    }
}

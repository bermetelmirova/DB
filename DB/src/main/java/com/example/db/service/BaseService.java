package com.example.db.service;

import java.util.List;

public interface BaseService<T> {
    T save(T entity);

    T deleteById(Long id);

    T update(T entity);

    List<T> getAll();

    T getById(Long id);
}

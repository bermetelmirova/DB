package com.example.db.bean;

public interface BeanService {
    void init();

    void create();

    void delete(Long id);

    String navigateToUpdate(Long id);

    void update();

    void clean();
}

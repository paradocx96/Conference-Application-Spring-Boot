package com.rhna.conference.domain;

import java.util.List;

public interface NewsDataAdapter {

    News save(News news);

    List<News> getAll();

    void deleteById(String id);

    News update(News news);

    List<News> getById(String id);

    News updateStatus(News news);

    List<News> getByStatus(String status);
}

package com.rhna.conference.domain;

import java.util.List;
import java.util.Optional;

public interface NewsDataAdapter {

    News save(News news);

    List<News> getAll();

    void deleteById(String id);

    News update(News news);

    Optional<News> getNewsById(String id);
}

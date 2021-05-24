package com.rhna.conference.api;

import com.rhna.conference.domain.NewsDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsApi {

    private final NewsDataAdapter newsDataAdapter;

    @Autowired
    public NewsApi(NewsDataAdapter newsDataAdapter) {
        this.newsDataAdapter = newsDataAdapter;
    }
}

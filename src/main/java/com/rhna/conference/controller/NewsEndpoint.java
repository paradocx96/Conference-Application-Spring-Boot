package com.rhna.conference.controller;

import com.rhna.conference.api.NewsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class NewsEndpoint {

    private final NewsApi newsApi;

    @Autowired
    public NewsEndpoint(NewsApi newsApi) {
        this.newsApi = newsApi;
    }
}

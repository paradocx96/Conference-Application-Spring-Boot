package com.rhna.conference.controller;

import com.rhna.conference.api.NewsApi;
import com.rhna.conference.domain.News;
import com.rhna.conference.dto.NewsDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class NewsEndpoint {

    private final NewsApi newsApi;

    @Autowired
    public NewsEndpoint(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public News addNews(@RequestBody NewsDto newsDto) {
        News news = new News();
        news.setDescription(newsDto.getDescription());
        news.setDate(newsDto.getDate());
        news.setStatus("Inactive");
        news.setUser(newsDto.getUser());

        return newsApi.addNews(news);
    }

    @GetMapping
    public List<News> getNewses() {
        return newsApi.getNewses();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNewsById(@PathVariable String id) {
        newsApi.deleteNewsById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public News updateNews(@RequestBody NewsDto newsDto, @PathVariable String id) {
        News news = new News();

        news.setId(id);
        news.setDescription(newsDto.getDescription());
        news.setDate(newsDto.getDate());
        news.setStatus(newsDto.getStatus());
        news.setUser(newsDto.getUser());

        return newsApi.updateNews(news);
    }

}

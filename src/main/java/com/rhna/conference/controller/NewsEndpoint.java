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
        // Create news object
        News news = new News();

        // Set values to news object
        news.setDescription(newsDto.getDescription());
        news.setDate(newsDto.getDate());
        news.setStatus("Inactive");
        news.setUser(newsDto.getUser());

        // Calling addNews method
        return newsApi.addNews(news);
    }

    @GetMapping
    public List<News> getNewses() {
        return newsApi.getNewses();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNewsById(@PathVariable String id) {
        // Getting id as parameter and calling delete method
        newsApi.deleteNewsById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public News updateNews(@RequestBody NewsDto newsDto, @PathVariable String id) {
        // Creating news object
        News news = new News();

        // Assign news id getting from path variable
        news.setId(id);

        // Set values to news object
        news.setDescription(newsDto.getDescription());
        news.setDate(newsDto.getDate());
        news.setStatus(newsDto.getStatus());
        news.setUser(newsDto.getUser());

        // Calling updateNews method
        return newsApi.updateNews(news);
    }

    @PutMapping("/updateStatus/{id}")
    @ResponseStatus(HttpStatus.OK)
    public News updateNewsStatus(@RequestBody NewsDto newsDto, @PathVariable String id) {
        // Creating news object
        News news = new News();

        // Set values to news object
        news.setId(id);
        news.setStatus(newsDto.getStatus());

        // Calling update status method passing news object
        return newsApi.updateStatus(news);
    }

    @GetMapping("/get-by-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<News> getNewsByStatus(@PathVariable String status) {
        // Calling get news by status method passing status as parameter
        return newsApi.getNewsByStatus(status);
    }

    @GetMapping("/get-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<News> getNewsById(@PathVariable String id) {
        // Calling get news by id method using parameter as path variable id
        return newsApi.getNewsById(id);
    }

}

package com.rhna.conference.api;

import com.rhna.conference.domain.News;
import com.rhna.conference.domain.NewsDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsApi {

    private final NewsDataAdapter newsDataAdapter;

    @Autowired
    public NewsApi(NewsDataAdapter newsDataAdapter) {
        this.newsDataAdapter = newsDataAdapter;
    }

    // Add new news method
    public News addNews(News news) {
        // Assign current date and time to news object
        news.setDatetime(LocalDateTime.now());

        // Calling save method in Adapter class and return value
        news = newsDataAdapter.save(news);
        return news;
    }

    // Getting all news method
    public List<News> getNewses() {
        // Getting values as array list
        return new ArrayList<>(newsDataAdapter.getAll());
    }

    // Update news method
    public News updateNews(News news) {
        // calling update method
        return newsDataAdapter.update(news);
    }

    // Delete news by id method
    public void deleteNewsById(String id) {
        // Calling delete method, id as parameter
        newsDataAdapter.deleteById(id);
    }

    // Update news status method
    public News updateStatus(News news) {
        // Calling update status method passing news object
        return  newsDataAdapter.updateStatus(news);
    }

    // Getting all news by status method
    public List<News> getNewsByStatus(String status) {
        // Getting values as array list. Passing status as parameter to call
        return new ArrayList<>(newsDataAdapter.getByStatus(status));
    }

    // News get by id method
    public List<News> getNewsById(String id) {
        // calling getbyid method using parameter as id
        return newsDataAdapter.getById(id);
    }
}

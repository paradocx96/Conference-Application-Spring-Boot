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

    public News addNews(News news) {
        news.setDatetime(LocalDateTime.now());
        news = newsDataAdapter.save(news);
        return news;
    }

    public List<News> getNewses() {
        return new ArrayList<>(newsDataAdapter.getAll());
    }

    public News updateNews(News news) {
        return newsDataAdapter.update(news);
    }

    public void deleteNewsById(String id) {
        newsDataAdapter.deleteById(id);
    }

    public News updateStatus(News news) {
        return  newsDataAdapter.updateStatus(news);
    }

    public List<News> getNewsByStatus(String status) {
        return new ArrayList<>(newsDataAdapter.getByStatus(status));
    }

    public List<News> getNewsById(String id) {
        return newsDataAdapter.getById(id);
    }
}

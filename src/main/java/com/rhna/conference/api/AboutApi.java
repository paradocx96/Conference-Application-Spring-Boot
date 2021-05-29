package com.rhna.conference.api;

import com.rhna.conference.domain.About;
import com.rhna.conference.domain.AboutDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AboutApi {

    private final AboutDataAdapter adapter;

    @Autowired
    public AboutApi(AboutDataAdapter adapter) {
        this.adapter = adapter;
    }

    public About saveAbout(About about) {
        about.setDatetime(LocalDateTime.now());
        about = adapter.saveAbout(about);
        return about;
    }

    public List<About> getAllAbout(){
        return new ArrayList<>(adapter.getAllAbout());
    }

    public List<About> getByIdAbout(String id) {
        return adapter.getByIdAbout(id);
    }

    public void deleteByIdAbout(String id) {
        adapter.deleteByIdAbout(id);
    }

    public About updateAbout(About about) {
        return adapter.updateAbout(about);
    }
}

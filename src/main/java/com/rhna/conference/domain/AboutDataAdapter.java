package com.rhna.conference.domain;

import java.util.List;

public interface AboutDataAdapter {

    About saveAbout(About about);

    List<About> getAllAbout();

    List<About> getByIdAbout(String id);

    void deleteByIdAbout(String id);

    About updateAbout(About about);
}

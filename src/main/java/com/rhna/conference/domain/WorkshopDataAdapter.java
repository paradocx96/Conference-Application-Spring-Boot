package com.rhna.conference.domain;

import java.util.List;

public interface WorkshopDataAdapter {
    Workshop save(Workshop workshop);

    List<Workshop> getAll();
}

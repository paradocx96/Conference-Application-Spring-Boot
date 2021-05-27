package com.rhna.conference.api;

import com.rhna.conference.domain.Workshop;
import com.rhna.conference.domain.WorkshopDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopAPI {

    private final WorkshopDataAdapter workshopDataAdapter;

    @Autowired
    public WorkshopAPI(WorkshopDataAdapter workshopDataAdapter) {
        this.workshopDataAdapter = workshopDataAdapter;
    }

    public Workshop addWorkshop(Workshop workshop) {
        workshop = workshopDataAdapter.save(workshop);
        return workshop;
    }

    public List<Workshop> getAllWorkshops() {
        return workshopDataAdapter.getAll();
    }
}

package com.rhna.conference.dal.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhna.conference.dal.model.WorkshopModel;
import com.rhna.conference.dal.repository.WorkshopMongoRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class WorkshopAdapterMongoImplTest {

    @InjectMocks
    @Autowired
    WorkshopAdapterMongoImpl workshopAdapterMongo;

    @Autowired
    WorkshopMongoRepository workshopMongoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveWorkshop();
    }

    private void saveWorkshop() {
        String workshopList = "[\n" +
                "{\n" +
                "    \"id\": \"workshopId_01\",\n" +
                "    \"username\": \"testUser_01\",\n" +
                "    \"title\": \"testTitle_01\",\n" +
                "    \"courseCode\": \"testCourseCode_01\",\n" +
                "    \"venue\": \"testVenue_01\",\n" +
                "    \"date\": \"testDate_01\",\n" +
                "    \"startingTime\": \"testStartingTime_01\",\n" +
                "    \"endTime\": \"testEndTime_01\",\n" +
                "    \"description\": \"testDescription_01\",\n" +
                "    \"documents\": null,\n" +
                "    \"published\": true,\n" +
                "    \"hasDocuments\": false\n" +
                "},\n" +
                "{\n" +
                "    \"id\": \"workshopId_02\",\n" +
                "    \"username\": \"testUser_02\",\n" +
                "    \"title\": \"testTitle_02\",\n" +
                "    \"courseCode\": \"testCourseCode_02\",\n" +
                "    \"venue\": \"testVenue_02\",\n" +
                "    \"date\": \"testDate_02\",\n" +
                "    \"startingTime\": \"testStartingTime_02\",\n" +
                "    \"endTime\": \"testEndTime_02\",\n" +
                "    \"description\": \"testDescription_02\",\n" +
                "    \"documents\": null,\n" +
                "    \"published\": false,\n" +
                "    \"hasDocuments\": false\n" +
                "}\n" +
                "]";
        try{
            WorkshopModel workshopModels[] = new ObjectMapper().readValue(workshopList, WorkshopModel[].class);
            for (WorkshopModel workshopModel1: workshopModels){
                workshopMongoRepository.save(workshopModel1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getAll() {
    }
}

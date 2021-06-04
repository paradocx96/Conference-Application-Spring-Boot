package com.rhna.conference.dal.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhna.conference.RhnaConferenceBackendApplicationTests;
import com.rhna.conference.dal.model.WorkshopModel;
import com.rhna.conference.dal.repository.WorkshopMongoRepository;
import com.rhna.conference.domain.Workshop;
import org.bson.types.Binary;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;

import java.util.List;

public class WorkshopAdapterMongoImplTest extends RhnaConferenceBackendApplicationTests {

    byte[] byteArray1 = {80, 65, 78, 75, 65, 74};
    Binary testDummyDocument = new Binary(byteArray1);

    @InjectMocks
    @Autowired
    WorkshopAdapterMongoImpl workshopAdapterMongo;

    @Autowired
    WorkshopMongoRepository workshopMongoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

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
        try {
            WorkshopModel[] workshopModels = new ObjectMapper().readValue(workshopList, WorkshopModel[].class);
            for (WorkshopModel workshopModel1 : workshopModels) {
                workshopMongoRepository.save(workshopModel1);
            }
            WorkshopModel newWorkshop = new WorkshopModel("testUserName_03", "testTitle_03",
                    "testCourseCode_03", "testVenue_03", "testDate_03", "testStartingTime_03",
                    "testEndTime_03", "testDescription_03", testDummyDocument);
            newWorkshop.setId("workshopId_03");
            workshopMongoRepository.save(newWorkshop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        workshopMongoRepository.deleteById("workshopId_01");
        workshopMongoRepository.deleteById("workshopId_02");
        workshopMongoRepository.deleteById("workshopId_03");
    }

    @Test
    void testSave() {
        Workshop newWorkshop = new Workshop();
        newWorkshop.setId("workshopId_04");
        newWorkshop.setUsername("dummyUserName");
        newWorkshop.setTitle("dummyTitle");
        newWorkshop.setCourseCode("dummyCourseCode");
        newWorkshop.setVenue("dummyVenue");
        newWorkshop.setDate("dummyDate");
        newWorkshop.setStartingTime("dummyStartingTime");
        newWorkshop.setEndTime("dummyEndTime");
        newWorkshop.setDescription("dummyDescription");
        newWorkshop.setDocuments(testDummyDocument);

        Workshop result = workshopAdapterMongo.save(newWorkshop);

        Assertions.assertEquals(result.getId(), "workshopId_04");
        Assertions.assertEquals(result.getUsername(), "dummyUserName");
        Assertions.assertEquals(result.getTitle(), "dummyTitle");
        Assertions.assertEquals(result.getCourseCode(), "dummyCourseCode");
        Assertions.assertEquals(result.getVenue(), "dummyVenue");
        Assertions.assertEquals(result.getDate(), "dummyDate");
        Assertions.assertEquals(result.getStartingTime(), "dummyStartingTime");
        Assertions.assertEquals(result.getEndTime(), "dummyEndTime");
        Assertions.assertEquals(result.getDescription(), "dummyDescription");
        Assertions.assertNotNull(result.getDocuments());
        workshopAdapterMongo.delete("workshopId_04"); // Remove the document from database.
    }

    @Test
    void testGetAll() {
        List<Workshop> results = workshopAdapterMongo.getAll();
        Assertions.assertTrue(results.size() >= 2);
    }

    @Test
    void testGetFileById() {
        HttpEntity<byte[]> result = workshopAdapterMongo.getFileById("workshopId_03");
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdate() {
        //Create a dummy updated field workshop with existing workshop id.
        Workshop existingWorkshop = new Workshop();
        existingWorkshop.setId("workshopId_01");
        existingWorkshop.setUsername("updatedUserName");
        existingWorkshop.setTitle("updatedTitle");
        existingWorkshop.setCourseCode("updatedCourseCode");
        existingWorkshop.setVenue("updatedVenue");
        existingWorkshop.setDate("updatedDate");
        existingWorkshop.setStartingTime("updatedStartingTime");
        existingWorkshop.setEndTime("updatedEndTime");
        existingWorkshop.setDescription("updatedDescription");
        existingWorkshop.setDocuments(null);

        Workshop result = workshopAdapterMongo.update(existingWorkshop);

        Assertions.assertEquals(result.getId(), "workshopId_01");
        Assertions.assertEquals(result.getUsername(), "updatedUserName");
        Assertions.assertEquals(result.getTitle(), "updatedTitle");
        Assertions.assertEquals(result.getCourseCode(), "updatedCourseCode");
        Assertions.assertEquals(result.getVenue(), "updatedVenue");
        Assertions.assertEquals(result.getDate(), "updatedDate");
        Assertions.assertEquals(result.getStartingTime(), "updatedStartingTime");
        Assertions.assertEquals(result.getEndTime(), "updatedEndTime");
        Assertions.assertEquals(result.getDescription(), "updatedDescription");
    }

    @Test
    void testGetAllPending() {
        List<Workshop> results = workshopAdapterMongo.getAllPending();
        for (Workshop result : results) {
            Assertions.assertEquals(result.getIsPublished(), false);
        }
    }

    @Test
    void testGetAllScheduled() {
        List<Workshop> results = workshopAdapterMongo.getAllScheduled();
        for (Workshop result : results) {
            Assertions.assertEquals(result.getIsPublished(), true);
        }
    }

    @Test
    void testDelete() {
        String result = workshopAdapterMongo.delete("workshopId_01");
        Assertions.assertEquals(result, "Deleted");
    }

    @Test
    void testApprove() {
        String result = workshopAdapterMongo.approve("workshopId_01");
        Assertions.assertEquals(result, "Approved");
    }
}

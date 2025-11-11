package com.api.test;

import com.api.pojo.CreateJobPayload;
import com.api.utils.FakeDataGenerator;
import com.api.utils.specUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class CreateJobApiTestWithFakerData {
   private CreateJobPayload createJobPayload;
   private static final String COUNTRY="India";

    @BeforeMethod(description = "creating the creat job api payload")
    public void setUp(){
//

        createJobPayload= FakeDataGenerator.generateFakeCreateJobData();
    }



    @Test(description ="Verifying the create Job api is able to create the inwarrenty job" ,groups = {"api","regression","smoke"})

    public void createJobApiTest() {

        given().spec(specUtil.RequestSpec_withHeader_Payload(FD, createJobPayload)).when().post("/job/create").then().spec(specUtil.ResponseSpec_Ok()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json")).body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"));
        // Test implementation will go here
    }
}

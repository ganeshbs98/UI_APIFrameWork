package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.*;

import com.api.constants.*;
import com.api.pojo.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.specUtil;
import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CreateJobApiTest {

    @Test(description ="Verifying the create Job api is able to create the inwarrenty job" ,groups = {"api","regression","smoke"})

    public void createJobApiTest() {
        System.out.println(Instant.now());
        System.out.println("***********************");




        given().spec(specUtil.RequestSpec_withHeader_Payload(FD, createJobPayload)).when().post("/job/create").then().spec(specUtil.ResponseSpec_Ok()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json")).body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"));
        // Test implementation will go here
    }
}

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

import java.sql.SQLOutput;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CreateJobApiTest {

    @Test
    public void createJobApiTest() {
        System.out.println(Instant.now());
        System.out.println("***********************");


        Customer customer = new Customer("Denis", "Boyer", "986-350-8890", "9823984974", "abc@gmail.com", "abc2@gmail.com");
        System.out.println(customer.first_name());
        Customer_Address customer_address = new Customer_Address("c 304", "Jupiter", "MG road", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
        Customer_Product customerProduct = new Customer_Product(getDateTimeWithAgo(10), "26889419449447", "26889419449447", "26889419449447", getDateTimeWithAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
        Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
        List<Problems> problemsList = new ArrayList<>();
        problemsList.add(problems);
        CreateJobPayload createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warrenty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customer_address, customerProduct, problemsList);

        given().spec(specUtil.RequestSpec_withHeader_Payload(FD, createJobPayload)).when().post("/job/create").then().spec(specUtil.ResponseSpec_Ok()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json")).body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"));
        // Test implementation will go here
    }
}

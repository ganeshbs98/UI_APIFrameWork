package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.*;

import com.api.constants.*;
import com.api.pojo.*;
import com.api.services.JobService;
import com.api.utils.*;

import static com.api.utils.DateTimeUtil.*;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreateJobApiTest {
   private CreateJobPayload createJobPayload;
   private JobService jobService;
   private static final String COUNTRY="India";

    @BeforeMethod(description = "creating the creat job api payload")
    public void setUp(){
        Customer customer = new Customer("Denis", "Boyer", "986-350-8890", "9823984974", "abc@gmail.com", "abc2@gmail.com");
        System.out.println(customer.first_name());
        Customer_Address customer_address = new Customer_Address("c 304", "Jupiter", "MG road", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
        Customer_Product customerProduct = new Customer_Product(getDateTimeWithAgo(10), "26867419449447", "26867419449447", "26867419449447", getDateTimeWithAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
        Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
        List<Problems> problemsList = new ArrayList<>();
        problemsList.add(problems);
        createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warrenty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customer_address, customerProduct, problemsList);
        jobService=new JobService();

    }



    @Test(description ="Verifying the create Job api is able to create the inwarrenty job" ,groups = {"api","regression","smoke"})

    public void createJobApiTest() {

        jobService.createJob(FD, createJobPayload).then().spec(specUtil.ResponseSpec_Ok()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json")).body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"));
        // Test implementation will go here
    }
}

package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.*;

import com.api.constants.*;
import com.api.pojo.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import static com.api.utils.DateTimeUtil.*;

import com.api.utils.DateTimeUtil;
import com.api.utils.specUtil;
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
   private static final String COUNTRY="India";

    @BeforeMethod(description = "creating the creat job api payload")
    public void setUp(){
//        Customer customer = new Customer("Denis", "Boyer", "986-350-8890", "9823984974", "abc@gmail.com", "abc2@gmail.com");
//        System.out.println(customer.first_name());
//        Customer_Address customer_address = new Customer_Address("c 304", "Jupiter", "MG road", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
//        Customer_Product customerProduct = new Customer_Product(getDateTimeWithAgo(10), "26889419449447", "26889419449447", "26889419449447", getDateTimeWithAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
//        Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
//        List<Problems> problemsList = new ArrayList<>();
//        problemsList.add(problems);
//        createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warrenty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customer_address, customerProduct, problemsList);

        Faker faker=new Faker(new Locale("en-IND"));
        String fName=faker.name().firstName();
        String lName=faker.name().lastName();
        String PNumber=faker.numerify("959#######");
        String AltPhoneNumber=faker.numerify("759#######");
        String Email=faker.internet().emailAddress();
        String AltEmail=faker.internet().emailAddress();
        Customer customer=new Customer(fName,lName,PNumber,AltPhoneNumber,Email,AltEmail);

        String FlatNo=faker.numerify("###");
        String apartment=faker.address().streetName();
        String streetName=faker.address().streetName();
        String landMark=faker.address().streetName();
        String areaName=faker.address().streetName();
        String pincode=faker.numerify("######");

        String state=faker.address().state();

        Customer_Address customerAddress=new Customer_Address(FlatNo,apartment,streetName,areaName,landMark,pincode,COUNTRY,state);
        System.out.println(customerAddress);
        String dop=DateTimeUtil.getDateTimeWithAgo(10);
        String imei=faker.numerify("###############");
        String popurl=faker.internet().url();
        Customer_Product customerProduct=new Customer_Product(dop,imei,imei,imei,popurl,1,1);

        int problmId= faker.random().nextInt(27)+1;
        Problems prob=new Problems(problmId,faker.lorem().sentence(5));
        List<Problems> problemList=new ArrayList<>();
        problemList.add(prob);
        createJobPayload=new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemList);
    }



    @Test(description ="Verifying the create Job api is able to create the inwarrenty job" ,groups = {"api","regression","smoke"})

    public void createJobApiTest() {

        given().spec(specUtil.RequestSpec_withHeader_Payload(FD, createJobPayload)).when().post("/job/create").then().spec(specUtil.ResponseSpec_Ok()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json")).body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"));
        // Test implementation will go here
    }
}

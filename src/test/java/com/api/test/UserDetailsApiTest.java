package com.api.test;

import static com.api.utils.DateTimeUtil.getDateTimeWithAgo;
import static io.restassured.RestAssured.*;

import static com.api.constants.Role.*;

import static  com.api.constants.Role.*;

import static com.api.constants.Role.*;

import com.api.constants.*;
import com.api.pojo.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.specUtil;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsApiTest {
    private CreateJobPayload createJobPayload;

    @BeforeMethod(description = "creating the creat job api payload")
    public void setUp(){
        Customer customer = new Customer("Denis", "Boyer", "986-350-8890", "9823984974", "abc@gmail.com", "abc2@gmail.com");
        System.out.println(customer.first_name());
        Customer_Address customer_address = new Customer_Address("c 304", "Jupiter", "MG road", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
        Customer_Product customerProduct = new Customer_Product(getDateTimeWithAgo(10), "26889419449447", "26889419449447", "26889419449447", getDateTimeWithAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
        Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
        List<Problems> problemsList = new ArrayList<>();
        problemsList.add(problems);
        createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warrenty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customer_address, customerProduct, problemsList);
    }


    @Test(description = "verfiy if the user details api response is  shown correctly",groups = {"api","regression","smoke"})
    public void UserDetailsApiTest(){
       given().spec(specUtil.RequestSpecAuth(FD))
                .when()
                .get("userdetails")
                .then()
                .spec(specUtil.ResponseSpec_Ok())
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
    }
}

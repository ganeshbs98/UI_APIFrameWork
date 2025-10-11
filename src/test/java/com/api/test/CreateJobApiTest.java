package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.*;

import com.api.pojo.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.specUtil;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CreateJobApiTest {

    @Test
    public void createJobApiTest() {

        Customer customer=new Customer("Denis","Boyer","986-350-8890","9823984974","abc@gmail.com","abc2@gmail.com");
        Customer_Address customer_address=new Customer_Address("c 304","Jupiter","MG road","Bangur Nagar","Goregaon West","411039","India","Maharashtra");
        Customer_Product customerProduct=new Customer_Product("2025-04-06T18:30:00.000Z","58989419449447","58989419449447","58989419449447","025-04-06T18:30:00.000Z",1,1);
        Problems problems=new Problems(1,"Battery Issue");
        Problems[] problemsArray=new Problems[1];
        problemsArray[0]=problems;
        CreateJobPayload createJobPayload=new CreateJobPayload(0,2,1,1,customer,customer_address,customerProduct,problemsArray);

        given().spec(specUtil.RequestSpec_withHeader_Payload(FD,createJobPayload)).when().post("/job/create").then().spec(specUtil.ResponseSpec_Ok());
        // Test implementation will go here
    }
}

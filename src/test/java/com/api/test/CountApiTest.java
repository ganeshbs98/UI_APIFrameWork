package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.api.constants.Role;
import static com.api.utils.AuthTokenProvider.*;

import com.api.services.DashBoardService;
import com.api.utils.ConfigManager;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import com.api.utils.specUtil;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Epic("Job Management")
@Feature("Count API Tests")
public class CountApiTest {
    DashBoardService dashBoardService;
    @BeforeMethod()
    public void setup(){
        dashBoardService= new DashBoardService();
    }


    @Story("Verify Count API response")
    @Description("Test to verify that the Count API returns the correct counts for various labels and adheres to the expected JSON schema.")
    @Severity(SeverityLevel.NORMAL)

    @Test(description ="Verifying the count api  is giving correct response" ,groups = {"api","regression","smoke"})

    public void verifyCountApiTest(){
        dashBoardService.getCount(FD)
                .then()
                .spec(specUtil.ResponseSpec_Ok())
                .body("message", Matchers.equalTo("Success"))
                .body("data",Matchers.notNullValue())
                .body("data.size()",Matchers.equalTo(3))
                .body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
                .body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
                .body(  matchesJsonSchemaInClasspath("response-schema/countApiResponseSchema.json"));

    }

    @Test(description ="Verifying the count api giving correct statuccode for invalid token" ,groups = {"api","negative","regression","smoke"})

    public void countApiTest_missingAuthToken(){
        dashBoardService.countWithNoAuth()
                .then()
                .spec(specUtil.ResponseSpec_Text(401));
    }

}

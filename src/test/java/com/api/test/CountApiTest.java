package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.api.constants.Role;
import static com.api.utils.AuthTokenProvider.*;
import com.api.utils.ConfigManager;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import com.api.utils.specUtil;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CountApiTest {

    @Test(description ="Verifying the count api  is giving correct response" ,groups = {"api","regression","smoke"})

    public void verifyCountApiTest(){

        given().spec(specUtil.RequestSpecAuth(FD))
                .when()
                .get("/dashboard/count")
                .then()
                .spec(specUtil.ResponseSpec_Ok())
                .body("message", Matchers.equalTo("Success"))
                .body("data",Matchers.notNullValue())
                .body("data.size()",Matchers.equalTo(3))
                .body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
                .body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
                .body(  matchesJsonSchemaInClasspath("response-schema/CountApiResponseSchema.json"));

    }

    @Test(description ="Verifying the count api giving correct statuccode for invalid token" ,groups = {"api","negative","regression","smoke"})

    public void countApiTest_missingAuthToken(){
        given().spec(specUtil.RequestSpec())
                .when()
                .get("/dashboard/count")
                .then()
                .spec(specUtil.ResponseSpec_Text(401));
    }

}

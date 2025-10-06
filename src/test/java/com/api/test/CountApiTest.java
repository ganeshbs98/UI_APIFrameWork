package com.api.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.api.constants.Role;
import static com.api.utils.AuthTokenProvider.*;
import com.api.utils.ConfigManager;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CountApiTest {

    @Test
    public void verifyCountApiTest(){

        given().baseUri(ConfigManager.loadProperties().getProperty("URI"))
                .and()
                .header("Authorization", getAuthToken(Role.FD))
                .log().uri()
                .log().method()
                .log().headers()
                .when()
                .get("/dashboard/count")
                .then()
                .log().status()
                .log().body()
                .time(lessThan(1000l))
                .statusCode(Matchers.equalTo(200))
                .body("message", Matchers.equalTo("Success"))
                .body("data",Matchers.notNullValue())
                .body("data.size()",Matchers.equalTo(3))
                .body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
                .body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
                .body(  matchesJsonSchemaInClasspath("response-schema/CountApiResponseSchema.json"));

//        System.out.println(res);

    }

    @Test
    public void countApiTest_missingAuthToken(){
        given().baseUri(ConfigManager.loadProperties().getProperty("URI"))
                .log().uri()
                .log().method()
                .log().headers()
                .when()
                .get("/dashboard/count")
                .then()
                .statusCode(401)
                .log().status()
                .log().body()
                .time(lessThan(500L));
    }

}

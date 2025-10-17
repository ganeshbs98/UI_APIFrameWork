package com.api.test;

import static io.restassured.RestAssured.*;

import static com.api.constants.Role.*;

import com.api.requestModel.*;
import com.api.utils.specUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

public class UserDetailsApiTest {
    private CreateJobPayload createJobPayload;




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

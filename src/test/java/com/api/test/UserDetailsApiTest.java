package com.api.test;

import static io.restassured.RestAssured.*;

import static com.api.constants.Role.*;

import static  com.api.constants.Role.*;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.specUtil;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserDetailsApiTest {
    @Test
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

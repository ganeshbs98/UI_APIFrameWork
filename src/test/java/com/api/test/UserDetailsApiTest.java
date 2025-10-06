package com.api.test;

import static io.restassured.RestAssured.*;

import static com.api.constants.Role.*;

import static  com.api.constants.Role.*;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserDetailsApiTest {
    @Test
    public void UserDetailsApiTest(){
        Header authHeader = new Header("Authorization", AuthTokenProvider.getAuthToken(FD));
       given().baseUri(ConfigManager.loadProperties().getProperty("URI"))
                .and()
                .header(authHeader)
                .and()
                .accept(ContentType.JSON)
                .log().uri()
                .log().headers()
                .log().method()
                .when()
                .get("userdetails")
                .then()
                .log().status()
               .log().all()
                .statusCode(200)
                .time(lessThan(1000L))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));


    }
}

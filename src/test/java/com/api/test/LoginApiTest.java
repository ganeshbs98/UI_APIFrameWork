package com.api.test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManagerOld;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LoginApiTest {

    @Test
    public void LoginApiTest() {
        UserCredentials userCredentials = new UserCredentials("iamfd", "password");
        Properties prop= ConfigManagerOld.loadProperties();

        System.out.println(prop.getProperty("URI"));
        Response res = given()
                .baseUri(ConfigManager.loadProperties().getProperty("URI"))
                .and()
                .contentType(ContentType.JSON)
                .and()
                .accept(ContentType.JSON)
                .and()
                .body(userCredentials)
                .log().uri()
                .log().method()
                .log().body()
                .log().headers()
                .when()
                .post("login")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .and()
                .body("message", equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"))
                .body("data.token", notNullValue())
                .extract().response();
        System.out.println(res.asPrettyString());

    }
}

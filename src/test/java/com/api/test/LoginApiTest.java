package com.api.test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManagerOld;
import com.api.utils.ConfigManager;
import com.api.utils.specUtil;
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
         given().spec(specUtil.RequestSpec_withPayload(userCredentials))
                .when()
                .post("login")
                .then()
                .spec(specUtil.ResponseSpec_Ok())
                .body("message", equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"))
                .body("data.token", notNullValue());
    }
}

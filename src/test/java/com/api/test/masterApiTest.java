package com.api.test;

import static io.restassured.RestAssured.*;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class masterApiTest {

    @Test
    public void VerifyMasterApiTest(){
        given().baseUri(ConfigManager.loadProperties().getProperty("URI")).and()
                .header("Authorization", AuthTokenProvider.getAuthToken(Role.FD))
                .and()
                .contentType("")
                .log().method()
                .when()
                .post("master")
                .then()
                .log().status()
                .log().all()
                .statusCode(200)
                .time(lessThan(500L))
                .body("message",equalTo("Success"))
                .body("data",notNullValue())
                .body("data",hasKey("mst_oem"))
                .body("data",hasKey("mst_model"))
                .body("data",hasKey("mst_action_status"))
                .body("$",hasKey("message"))
                .body("$",hasKey("data"))
                .body("data.mst_oem.size()",greaterThanOrEqualTo(0))
                .body("data.mst_model.size()",greaterThanOrEqualTo(0))
                .body("data.mst_action_status.size()",greaterThanOrEqualTo(0))
                .body("ata.mst_oem_status.id",everyItem(Matchers.notNullValue()))
                .body("data.mst_action_status.id",everyItem(Matchers.notNullValue()))
                .body("data.mst_model.id",everyItem(Matchers.notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/masterApiResponseSchema.json"));
    }

    @Test
    public void inValid_TokenMasterApiTest(){
        given().baseUri(ConfigManager.loadProperties().getProperty("URI")).and()
                .header("Authorization","")
                .and()
                .contentType("")
                .log().uri()
                .log().method()
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(401)
                .time(lessThan(500L))
        ;
    }
}

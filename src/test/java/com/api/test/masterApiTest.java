package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.*;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.specUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class masterApiTest {

    @Test(description ="Verifying the master api giving correct response" ,groups = {"api","regression","smoke"})
    public void VerifyMasterApiTest(){
        given().spec(specUtil.RequestSpecAuth(FD))
                .when()
                .post("master")
                .then()
                .spec(specUtil.ResponseSpec_Ok())
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
                .body("data.mst_oem.id",everyItem(notNullValue()))
                .body("data.mst_oem.name",everyItem(notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/masterApiResponseSchema.json"));
    }

    @Test(description ="Verifying the master api giving correct status code for invalid token" ,groups = {"api","negative","regression","smoke"})
    public void inValid_TokenMasterApiTest(){
        given().spec(specUtil.RequestSpec())
                .when()
                .post("master")
                .then()
                .spec(specUtil.ResponseSpec_Text(401))
        ;
    }
}

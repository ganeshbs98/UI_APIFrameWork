package com.api.dataDrivenTest;

import com.api.pojo.UserCredentials;
import com.api.pojo.UserPojo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.utils.specUtil.RequestSpec_withPayload;
import static com.api.utils.specUtil.ResponseSpec_Ok;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginApiDataDrivenTest {


    @Test(description = "Verifying the Login Api is working for Iamfd",groups = {"api","regression","smoke"}, dataProviderClass = com.dataproviders.dataproviderUtils.class,dataProvider = "LoginApiDataProvider")
    public void LoginApiTest(UserPojo userPojo) {
         given().spec(RequestSpec_withPayload(userPojo))
                .when()
                .post("login")
                .then()
                .spec(ResponseSpec_Ok())
                .body("message", equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"))
                .body("data.token", notNullValue());
    }
}

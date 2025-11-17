package com.api.test.dataDrivenTest;

import com.api.pojo.UserCredentials;
import com.api.pojo.UserPojo;
import org.testng.annotations.Test;

import static com.api.utils.specUtil.RequestSpec_withPayload;
import static com.api.utils.specUtil.ResponseSpec_Ok;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginApiJsonDataDrivenTest {


    @Test(description = "Verifying the Login Api is working for Iamfd",groups = {"api","regression","smoke"}, dataProviderClass = com.dataproviders.dataproviderUtils.class,dataProvider = "LoginApiJsonDataProvider")
    public void LoginApiTest(UserCredentials userCredentials) {
         given().spec(RequestSpec_withPayload(userCredentials))
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

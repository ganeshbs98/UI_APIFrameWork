package com.api.test;
import com.api.pojo.UserCredentials;
import static com.api.utils.specUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import com.api.services.AuthService;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class LoginApiTest {
   private UserCredentials userCredentials;
    private AuthService authService;
    @BeforeMethod(description = "Setting up the pre-requisite for Login Api Test")
    public void setUp(){
        userCredentials=new UserCredentials("iamfd","password");
        authService=new AuthService();
    }

    @Test(description = "Verifying the Login Api is working for Iamfd",groups = {"api","regression","smoke"})
    public void LoginApiTest() {
                authService.login(userCredentials).then()
                .spec(ResponseSpec_Ok())
                .body("message", equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"))
                .body("data.token", notNullValue());

    }
}

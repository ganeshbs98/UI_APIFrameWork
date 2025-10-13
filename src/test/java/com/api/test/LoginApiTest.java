package com.api.test;
import com.api.pojo.UserCredentials;
import static com.api.utils.specUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class LoginApiTest {
    UserCredentials userCredentials;
    @BeforeMethod(description = "Setting up the pre-requisite for Login Api Test")
    public void setUp(){
        userCredentials=new UserCredentials("iamfd","password");
    }

    @Test(description = "Verifying the Login Api is working for Iamfd",groups = {"api","regression","smoke"})
    public void LoginApiTest() {
        UserCredentials userCredentials = new UserCredentials("iamfd", "password");
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

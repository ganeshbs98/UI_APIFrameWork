package com.api.utils;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.api.requestModel.UserCredentials;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class AuthTokenProvider {
    public static String getAuthToken(Role role){
        UserCredentials userCredentials=null;
        if(role==FD){
            userCredentials=new UserCredentials("iamfd", "password");
        } else if (role==SUP) {
            userCredentials=new UserCredentials("iamsup", "password");

        } else if (role==ENG) {
            userCredentials=new UserCredentials("iameng", "password");

        }else if(role==QC){
            userCredentials=new UserCredentials("iamqc", "password");

        }
        String token=given().baseUri(ConfigManager.loadProperties().getProperty("URI")).contentType(ContentType.JSON).body(userCredentials).when().post("login").then().log().ifValidationFails().body("message", equalTo("Success")).extract().body().jsonPath().getString("data.token");
       return token;

    }
}

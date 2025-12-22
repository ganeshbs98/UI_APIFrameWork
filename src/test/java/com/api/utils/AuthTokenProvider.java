package com.api.utils;

import static com.api.constants.Role.*;
import com.api.constants.Role;
import com.api.pojo.UserCredentials;
import io.restassured.http.ContentType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class AuthTokenProvider {
    private static Map<Role, String> tokenCache = new ConcurrentHashMap<Role,String>();
    public static String getAuthToken(Role role) {
        UserCredentials userCredentials = null;
        if (tokenCache.containsKey(role)) {
            return tokenCache.get(role);
        }
        if (role == FD) {
            userCredentials = new UserCredentials("iamfd", "password");
        } else if (role == SUP) {
            userCredentials = new UserCredentials("iamsup", "password");

        } else if (role == ENG) {
            userCredentials = new UserCredentials("iameng", "password");

        } else if (role == QC) {
            userCredentials = new UserCredentials("iamqc", "password");

        }
        String token = given().baseUri(ConfigManager.loadProperties().getProperty("URI")).contentType(ContentType.JSON).body(userCredentials).when().post("login").then().log().ifValidationFails().body("message", equalTo("Success")).extract().body().jsonPath().getString("data.token");
        tokenCache.put(role, token);
        return token;

    }
}

package com.api.utils;

import static com.api.constants.Role.*;
import com.api.constants.Role;
import com.api.pojo.UserCredentials;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class AuthTokenProvider {
    private static final String LOGIN_ENDPOINT="login";
    private static final Logger logger= LogManager.getLogger(AuthTokenProvider.class);
    private static Map<Role, String> tokenCache = new ConcurrentHashMap<Role,String>();

    @Step("Getting auth token for the role: {role}")
    public static String getAuthToken(Role role) {
        logger.info("Checking if the token for the Role is chached:",role);
        UserCredentials userCredentials = null;
        if (tokenCache.containsKey(role)) {
            logger.info("Token is Found for the Role",role);
            return tokenCache.get(role);
        }
        logger.info("Token is not found in the cache, making the request for token:",role);
        if (role == FD) {
            userCredentials = new UserCredentials("iamfd", "password");
        } else if (role == SUP) {
            userCredentials = new UserCredentials("iamsup", "password");

        } else if (role == ENG) {
            userCredentials = new UserCredentials("iameng", "password");

        } else if (role == QC) {
            userCredentials = new UserCredentials("iamqc", "password");

        }
        logger.info("Fetching the token from the API for the role: with the endpoint",role);
        String token = given().spec(specUtil.RequestSpec_withPayload(userCredentials)).when().post(LOGIN_ENDPOINT).then().log().ifValidationFails().body("message", equalTo("Success")).extract().body().jsonPath().getString("data.token");
        logger.info("Token is cached for the Future use for the role:",role);
        tokenCache.put(role, token);
        return token;

    }
}

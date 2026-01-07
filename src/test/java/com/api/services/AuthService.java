package com.api.services;

import com.api.pojo.UserCredentials;
import com.api.utils.specUtil;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.*;

public class AuthService {
    private static final Logger logger= LogManager.getLogger(AuthService.class);

    private static final String LOGIN_ENDPOINT = "/login";

    public Response login(Object userCredentials){
        logger.info("Making the logging request for the payload{}:",((UserCredentials)userCredentials).username());
        Response res=given().spec(specUtil.RequestSpec_withPayload(userCredentials)).when().post(LOGIN_ENDPOINT);
        return res;

    }
}

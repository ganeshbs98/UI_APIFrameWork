package com.api.services;

import com.api.pojo.UserCredentials;
import com.api.utils.specUtil;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class AuthService {

    private static final String LOGIN_ENDPOINT = "/login";

    public Response login(UserCredentials userCredentials){
        Response res=given().spec(specUtil.RequestSpec_withPayload(userCredentials)).when().post(LOGIN_ENDPOINT);
        return res;

    }
}

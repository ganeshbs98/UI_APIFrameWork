package com.api.services;

import com.api.constants.Role;
import com.api.utils.specUtil;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class MasterService {

    private static final String MASTER_ENDPOINT="/master";

    public Response getMasterData(Role role){
       Response res= given().spec(specUtil.RequestSpecAuth(role)).when().get(MASTER_ENDPOINT);
       return res;
    }
}

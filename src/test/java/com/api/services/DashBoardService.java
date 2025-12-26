package com.api.services;

import com.api.constants.Role;
import com.api.utils.specUtil;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DashBoardService {

    private  static final String COUNT_ENDPOINT="/dashboard/count";

    public Response getCount(Role role){
        return given().spec(specUtil.RequestSpecAuth(role)).when().get(COUNT_ENDPOINT);
    }

    public Response countWithNoAuth(){
        return given().spec(specUtil.RequestSpec()).when().get(COUNT_ENDPOINT);
    }
}

package com.api.services;

import com.api.constants.Role;
import com.api.utils.specUtil;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DashBoardService {

    private  static final String COUNT_ENDPOINT="/dashboard/count";
    private  static final String DETAILS_COUNT_ENDPOINT="/dashboard/details";

    public Response getCount(Role role){
        return given().spec(specUtil.RequestSpecAuth(role)).when().get(COUNT_ENDPOINT);
    }

    public Response countWithNoAuth(){
        return given().spec(specUtil.RequestSpec()).when().get(COUNT_ENDPOINT);
    }
    public Response details(Role role,Object payload){
        return given().spec(specUtil.RequestSpecAuth(role)).body(payload).when().get(DETAILS_COUNT_ENDPOINT);
    }
}

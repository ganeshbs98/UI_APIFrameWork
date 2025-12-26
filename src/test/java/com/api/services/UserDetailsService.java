package com.api.services;

import com.api.utils.specUtil;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserDetailsService {

    private static final String USER_DETAILS_ENDPOINT="/userdetails";

    public Response getUserDetails(){
       Response res= given().spec(specUtil.RequestSpec()).when().get(USER_DETAILS_ENDPOINT);
       return res;
    }
}

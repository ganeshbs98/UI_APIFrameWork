package com.api.services;

import com.api.constants.Role;
import com.api.utils.specUtil;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class DashBoardService {
    private static final Logger logger= LogManager.getLogger(DashBoardService.class);
    private  static final String COUNT_ENDPOINT="/dashboard/count";
    private  static final String DETAILS_COUNT_ENDPOINT="/dashboard/details";

    public Response getCount(Role role){
        logger.info("Feteching the dashboard count for the role:{} for endpoint",role,COUNT_ENDPOINT);
        return given().spec(specUtil.RequestSpecAuth(role)).when().get(COUNT_ENDPOINT);
    }

    public Response countWithNoAuth(){
        logger.info("Feteching the dashboard count with no authentication");
        return given().spec(specUtil.RequestSpec()).when().get(COUNT_ENDPOINT);
    }
    public Response details(Role role,Object payload){
        logger.info("Feteching the dashboard details count for the role:{} with payload:{}",role,payload);
        return given().spec(specUtil.RequestSpecAuth(role)).body(payload).when().get(DETAILS_COUNT_ENDPOINT);
    }
}

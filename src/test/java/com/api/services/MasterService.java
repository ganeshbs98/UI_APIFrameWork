package com.api.services;

import com.api.constants.Role;
import com.api.utils.specUtil;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class MasterService {
    private static final Logger logger= LogManager.getLogger(MasterService.class);

    private static final String MASTER_ENDPOINT="/master";

    public Response getMasterData(Role role){
        logger.info("Fetching master data for the role: {}",role);
       Response res= given().spec(specUtil.RequestSpecAuth(role)).when().get(MASTER_ENDPOINT);
       return res;
    }
}

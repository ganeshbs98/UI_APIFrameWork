package com.api.services;

import com.api.utils.specUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.*;

public class UserDetailsService {
    private static final Logger logger= LogManager.getLogger(UserDetailsService.class);

    private static final String USER_DETAILS_ENDPOINT="/userdetails";
    @Step("Fetching user details with no authentication")
    public Response getUserDetails(){
        logger.info("Fetching user details");
       Response res= given().spec(specUtil.RequestSpec()).when().get(USER_DETAILS_ENDPOINT);
       return res;
    }
}

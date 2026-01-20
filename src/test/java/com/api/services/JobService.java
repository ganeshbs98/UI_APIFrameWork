package com.api.services;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.utils.specUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class JobService {
    private static final Logger logger =LogManager.getLogger(JobService.class);
    private static final String CREATE_JOB_ENDPOINT="/job/create";
    private static final String SEARCH_JOB_ENDPOINT="/job/search";

    @Step("Creating job api for role: {role} with payload: {createJobPayload}")
    public Response createJob(Role role, Object createJobPayload){
        // Implementation for creating a job
        logger.info("Creating job with payload: {}", createJobPayload);
        return given().spec(specUtil.RequestSpec_withHeader_Payload(role, createJobPayload)).when().post(CREATE_JOB_ENDPOINT);
    }
    @Step("Searching job api for role: {role} with payload: {searchPayload}")
    public Response searchJob(Role role,Object searchPayload){
        // Implementation for searching a job
        logger.info("Searching job with payload: {}", searchPayload);
        return given().spec(specUtil.RequestSpec_withHeader_Payload(role, searchPayload)).when().post(SEARCH_JOB_ENDPOINT);
    }

}


package com.api.services;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.utils.specUtil;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class JobService {
    private static final String CREATE_JOB_ENDPOINT="/job/create";
    private static final String SEARCH_JOB_ENDPOINT="/job/search";

    public Response createJob(Role role, Object createJobPayload){
        // Implementation for creating a job
        return given().spec(specUtil.RequestSpec_withHeader_Payload(role, createJobPayload)).when().post(CREATE_JOB_ENDPOINT);
    }

    public Response searchJob(Role role,Object searchPayload){
        // Implementation for searching a job
        return given().spec(specUtil.RequestSpec_withHeader_Payload(role, searchPayload)).when().post(SEARCH_JOB_ENDPOINT);
    }

}


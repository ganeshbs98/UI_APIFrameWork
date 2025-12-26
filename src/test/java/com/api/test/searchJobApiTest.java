package com.api.test;

import com.api.pojo.SearchJob;
import com.api.services.JobService;
import com.api.utils.specUtil;
import org.testng.annotations.BeforeMethod;

import static com.api.constants.Role.FD;
import static org.hamcrest.Matchers.*;

public class searchJobApiTest {
    private JobService jobService;
    private SearchJob searchJob;
    private static final String JOB_NUMBER="JOB_131343";

    @BeforeMethod(description = "Instantiating the Job service and creating search job payload")
    public void setup(){
        jobService=new JobService();
        searchJob=new SearchJob(JOB_NUMBER);
    }

    public void verifySearchJobApiTest(){
        jobService.searchJob(FD,searchJob)
                .then()
                .spec(specUtil.ResponseSpec_Ok())
                .body("message",equalTo("Success"));
    }
}

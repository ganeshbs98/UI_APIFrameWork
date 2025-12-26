package com.api.test;

import com.api.pojo.Details;
import com.api.services.DashBoardService;
import com.api.utils.specUtil;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DetailsApiTest {
    private DashBoardService dashBoardService;
    private Details details;

    @BeforeMethod(description = "Instantiatingteh dashBoard service and creating details payload")
    public void setUp(){
        dashBoardService = new DashBoardService();
        details = new Details("created_today");
    }

    @Test
    public void verifyDetailsApiTest(){
        dashBoardService.details(com.api.constants.Role.FD,details)
                .then()
                .spec(specUtil.ResponseSpec_Ok())
                .body("message", equalTo("Success"));
    }
}

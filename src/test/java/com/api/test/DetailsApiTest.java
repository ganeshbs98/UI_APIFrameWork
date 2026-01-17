package com.api.test;

import com.api.pojo.Details;
import com.api.services.DashBoardService;
import com.api.utils.specUtil;

import static com.api.constants.Role.FD;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Epic("Job Management")
@Feature("Job Details")
public class DetailsApiTest {
    private DashBoardService dashBoardService;
    private Details details;

    @BeforeMethod(description = "Instantiatingteh dashBoard service and creating details payload")
    public void setUp(){
        dashBoardService = new DashBoardService();
        details = new Details("created_today");
    }
    @Story("Verify Details API response")
    @Description("Test to verify that the Job Details API returns the correct job details based on the provided criteria.")
    @Severity(SeverityLevel.CRITICAL)

    @Test
    public void verifyDetailsApiTest(){
        dashBoardService.details(FD,details)
                .then()
                .spec(specUtil.ResponseSpec_Ok())
                .body("message", equalTo("Success"));
    }
}

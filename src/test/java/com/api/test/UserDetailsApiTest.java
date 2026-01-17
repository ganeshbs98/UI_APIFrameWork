package com.api.test;

import static com.api.utils.DateTimeUtil.getDateTimeWithAgo;
import static io.restassured.RestAssured.*;

import static com.api.constants.Role.*;

import static com.api.constants.Role.*;

import static com.api.constants.Role.*;

import com.api.constants.*;
import com.api.pojo.*;
import com.api.services.DashBoardService;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.specUtil;
import io.qameta.allure.*;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Epic("User Management")
@Feature("User Details API Tests")
public class UserDetailsApiTest {
    private CreateJobPayload createJobPayload;
    private DashBoardService dashBoardService;


    @Story("Verify User Details API response")
    @Description("Test to verify that the User Details API returns the correct user information and adheres to the expected JSON schema.")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "verfiy if the user details api response is  shown correctly", groups = {"api", "regression", "smoke"})
    public void UserDetailsApiTest() {
        given().spec(specUtil.RequestSpecAuth(FD))
                .when()
                .get("userdetails")
                .then()
                .spec(specUtil.ResponseSpec_Ok())
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
    }
}

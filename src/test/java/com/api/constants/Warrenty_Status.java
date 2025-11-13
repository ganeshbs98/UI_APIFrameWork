package com.api.constants;

import com.api.pojo.CreateJobPayload;
import com.api.utils.specUtil;
import org.testng.annotations.Test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public enum Warrenty_Status {
    IN_WARRANTY(1),OUT_OF_WARRANTY(2),EXTENDED_WARRANTY(3),NO_WARRANTY(4);
   private int code;

   private Warrenty_Status(int code){
       this.code=code;
   }

   public int getCode(){
       return code;
   }

    public static class createJobApiFakerDataDrivenTest {

        @Test(description ="Verifying the create Job api is able to create the inwarrenty job" ,groups = {"api","regression","smoke","dataDriven"},dataProviderClass = com.dataproviders.dataproviderUtils.class,dataProvider = "CreateJobAPIFakerDataProvider")

        public void createJobApiTest(CreateJobPayload createJobPayload) {

            given().spec(specUtil.RequestSpec_withHeader_Payload(FD, createJobPayload)).when().post("/job/create").then().spec(specUtil.ResponseSpec_Ok()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json")).body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"));
            // Test implementation will go here
        }

    }
}

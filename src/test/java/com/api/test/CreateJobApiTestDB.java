package com.api.test;

import com.api.constants.*;
import com.api.pojo.*;
import com.api.utils.specUtil;
import com.database.dao.*;
import com.database.model.*;
import com.database.reponse.model.CreateJobReponseModel;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.api.constants.Role.FD;
import static com.api.utils.DateTimeUtil.getDateTimeWithAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class CreateJobApiTestDB {
   private CreateJobPayload createJobPayload;
   private static final String COUNTRY="India";
   private Customer customer;
   private Customer_Address customer_address;
   private Customer_Product customerProduct;
   private Problems problems;

    @BeforeMethod(description = "creating the creat job api payload")
    public void setUp(){
       customer = new Customer("Denis", "Boyer", "986-350-8890", "9823984974", "abc@gmail.com", "abc2@gmail.com");
        System.out.println(customer.first_name());
         customer_address = new Customer_Address("c 304", "Jupiter", "MG road", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
         customerProduct = new Customer_Product(getDateTimeWithAgo(10), "20684545989447", "20684545989447", "20684545989447", getDateTimeWithAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
         problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
        List<Problems> problemsList = new ArrayList<>();
        problemsList.add(problems);
        createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warrenty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customer_address, customerProduct, problemsList);


    }



    @Test(description ="Verifying the create Job api is able to create the inwarrenty job" ,groups = {"api","regression","smoke"})

    public void createJobApiTest() {

         CreateJobReponseModel createJobReponseModel =given().spec(specUtil.RequestSpec_withHeader_Payload(FD, createJobPayload)).when().post("/job/create").then().spec(specUtil.ResponseSpec_Ok()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json")).body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_")).extract().as(CreateJobReponseModel.class);
        // Test implementation will go here
        System.out.println(createJobReponseModel);
        int customerId=createJobReponseModel.getData().getTr_customer_id();
        System.out.println(customerId);
        CustomerModel customerDataDB=CustomerDao.getCustomerInfo(customerId);
        System.out.println(customerDataDB);
        System.out.println("------------------Customer Details Validation -------------------");
        Assert.assertEquals(customer.first_name(),customerDataDB.getFirstName());
        Assert.assertEquals(customer.last_name(),customerDataDB.getLastName());
        Assert.assertEquals(customer.mobile_number(),customerDataDB.getMobile_number());
        Assert.assertEquals(customer.mobile_number_alt(),customerDataDB.getMobile_number_alt());
        Assert.assertEquals(customer.email_id(),customerDataDB.getEmail_id());
        Assert.assertEquals(customer.email_id_alt(),customerDataDB.getEmail_id_alt());
        System.out.println("------------------Customer Address Validation -------------------");
        CustomerAddressModel customerAddressModel =CustomerAddressDao.getCustomerAddress(customerDataDB.getTr_customer_address_id());
        Assert.assertEquals(customer_address.flat_number(),customerAddressModel.getFlat_number());
        Assert.assertEquals(customer_address.apartment_name(),customerAddressModel.getApartment_name());
        Assert.assertEquals(customer_address.street_name(),customerAddressModel.getStreet_name());
        Assert.assertEquals(customer_address.landmark(),customerAddressModel.getLandmark());
        Assert.assertEquals(customer_address.area(),customerAddressModel.getArea());
        Assert.assertEquals(customer_address.pincode(),customerAddressModel.getPincode());
        Assert.assertEquals(customer_address.country(),customerAddressModel.getCountry());
        Assert.assertEquals(customer_address.state(),customerAddressModel.getState());
        System.out.println("------------------Customer Address Validation Completed-------------------");
        CustomerProductModel customerProductModel= CustomerProductDao.getCustomerProductInfo(createJobReponseModel.getData().getTr_customer_product_id());

        int tr_job_head_id=createJobReponseModel.getData().getId();
        MapJobProblemModel mapJobProblemModel= MapJobProblemDao.getProblemsDetails(tr_job_head_id);
        System.out.println("------------------Job Problem Validation -------------------");
        Assert.assertEquals(mapJobProblemModel.getMst_problem_id(),createJobPayload.problems().get(0).id());
        Assert.assertEquals(mapJobProblemModel.getRemark(),createJobPayload.problems().get(0).remark());
        System.out.println("------------------Job Problem Validation Completed-------------------");
        int customer_Id=createJobReponseModel.getData().getId();
        JobHeadModel jobHeadModel= JobHeadDao.getJobHeadDetails(customer_Id);
        System.out.println("------------------Job Head Validation -------------------");
        Assert.assertEquals(jobHeadModel.getMst_oem_id(),createJobPayload.mst_oem_id());
        Assert.assertEquals(jobHeadModel.getMst_platform_id(),createJobPayload.mst_platform_id());
        Assert.assertEquals(jobHeadModel.getMst_service_location_id(),createJobPayload.mst_service_location_id());
        Assert.assertEquals(jobHeadModel.getMst_warrenty_status_id(),createJobPayload.mst_warrenty_status_id());
        System.out.println("------------------Job Head Validation Completed-------------------");
        System.out.println("------------------Customer Product Validation -------------------");
        Assert.assertEquals(customerProduct.dop(),customerProductModel.getDop());
        Assert.assertEquals(customerProduct.imei1(),customerProductModel.getImei1());
        Assert.assertEquals(customerProduct.imei2(),customerProductModel.getImei2());
        Assert.assertEquals(customerProduct.serial_number(),customerProductModel.getSerial_number());
        Assert.assertEquals(customerProduct.popurl(),customerProductModel.getPopurl());
        Assert.assertEquals(customerProduct.mst_model_id(),customerProductModel.getMst_model_id());
        System.out.println("------------------Customer Product Validation Completed-------------------");

    }
}

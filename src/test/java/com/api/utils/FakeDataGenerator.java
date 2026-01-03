package com.api.utils;

import com.api.pojo.*;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FakeDataGenerator {
    private static final Logger logger= LogManager.getLogger(FakeDataGenerator.class);

    private static Faker faker = new Faker();
    private static final String COUNTRY = "India";
    private static final int MST_SERVICE_LOCATION_ID = 0;
    private static final int MST_PLATFORM_ID = 2;
    private static final int MST_WARRENTY_STATUS_ID = 1;
    private static final int MST_OEM_ID = 1;
    private static final int PRODUCT_ID = 1;
    private static final int MST_MODEL_ID = 1;
    private static final int VALID_PROBLEM_ID[] = {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};

    private FakeDataGenerator() {

    }

    public static CreateJobPayload generateFakeCreateJobData() {
        logger.info("Generating fake CreateJobPayload data");
        Customer customer = generateFakeCustomerData();
        Customer_Address customerAddress = generateFakeCustomerAddressData();
        Customer_Product customerProduct = generateFakeCustomerProductData();
        List<Problems> problemsList = generateFakeProblemList();
        CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
        return createJobPayload;
    }
    public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
        logger.info("Generating the fake data for count:"+count);
        List<CreateJobPayload> payloadList=new ArrayList<>();
        for(int i=1;i<=count;i++) {
            Customer customer = generateFakeCustomerData();
            Customer_Address customerAddress = generateFakeCustomerAddressData();
            Customer_Product customerProduct = generateFakeCustomerProductData();
            List<Problems> problemsList = generateFakeProblemList();
            CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
            payloadList.add(createJobPayload);
        }
        return payloadList.iterator();
    }

    private static List<Problems> generateFakeProblemList() {
        logger.info("Generating fake Problems list");
        int count= faker.random().nextInt( 3)+1;
        List<Problems> problemList = new ArrayList<>();
        int problmId=0;
        Problems prob;
        for(int i=1;i<=count;i++){
            problmId = faker.random().nextInt(VALID_PROBLEM_ID.length);
            prob = new Problems(VALID_PROBLEM_ID[problmId], faker.lorem().sentence(5));
            problemList.add(prob);
        }
        return problemList;
    }

    private static Customer_Product generateFakeCustomerProductData() {
        logger.info("Generating fake Customer_Product data");
        String dop = DateTimeUtil.getDateTimeWithAgo(10);
        String imei = faker.numerify("###############");
        String popurl = faker.internet().url();
        Customer_Product customerProduct = new Customer_Product(dop, imei, imei, imei, popurl, PRODUCT_ID, MST_MODEL_ID);
        return customerProduct;
    }

    private static Customer_Address generateFakeCustomerAddressData() {
        logger.info("Generating fake Customer_Address data");
        String FlatNo = faker.numerify("###");
        String apartment = faker.address().streetName();
        String streetName = faker.address().streetName();
        String landMark = faker.address().streetName();
        String areaName = faker.address().streetName();
        String pincode = faker.numerify("######");

        String state = faker.address().state();

        Customer_Address customerAddress = new Customer_Address(FlatNo, apartment, streetName, areaName, landMark, pincode, COUNTRY, state);
        return customerAddress;
    }

    private static Customer generateFakeCustomerData() {
        logger.info("Generating fake Customer data");
        String fName = faker.name().firstName();
        String lName = faker.name().lastName();
        String PNumber = faker.numerify("959#######");
        String AltPhoneNumber = faker.numerify("759#######");
        String Email = faker.internet().emailAddress();
        String AltEmail = faker.internet().emailAddress();
        Customer customer = new Customer(fName, lName, PNumber, AltPhoneNumber, Email, AltEmail);
        return customer;
    }
}

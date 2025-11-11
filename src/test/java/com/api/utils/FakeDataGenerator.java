package com.api.utils;

import com.api.pojo.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FakeDataGenerator {

    private static Faker faker = new Faker();
    private static final String COUNTRY = "India";
    private static final int MST_SERVICE_LOCATION_ID = 0;
    private static final int MST_PLATFORM_ID = 2;
    private static final int MST_WARRENTY_STATUS_ID = 1;
    private static final int MST_OEM_ID = 1;
    private static final int PRODUCT_ID = 1;
    private static final int MST_MODEL_ID = 1;

    private FakeDataGenerator() {

    }

    public static CreateJobPayload generateFakeCreateJobData() {
        Customer customer = generateFakeCustomerData();
        Customer_Address customerAddress = generateFakeCustomerAddressData();
        Customer_Product customerProduct = generateFakeCustomerProductData();
        List<Problems> problemsList = generateFakeProblemList();
        CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
        return createJobPayload;
    }
    public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
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
        int problmId = faker.random().nextInt(27) + 1;
        Problems prob = new Problems(problmId, faker.lorem().sentence(5));
        List<Problems> problemList = new ArrayList<>();
        problemList.add(prob);
        return problemList;
    }

    private static Customer_Product generateFakeCustomerProductData() {
        String dop = DateTimeUtil.getDateTimeWithAgo(10);
        String imei = faker.numerify("###############");
        String popurl = faker.internet().url();
        Customer_Product customerProduct = new Customer_Product(dop, imei, imei, imei, popurl, PRODUCT_ID, MST_MODEL_ID);
        return customerProduct;
    }

    private static Customer_Address generateFakeCustomerAddressData() {
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

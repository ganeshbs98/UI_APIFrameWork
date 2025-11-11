package com.api.utils;

import com.api.pojo.*;
import com.github.javafaker.Faker;
import org.checkerframework.checker.units.qual.C;
import org.testng.reporters.ICustomizeXmlReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FakerDemo2 {
    private static final String COUNTRY= "India";
    public static void main(String[] args) {
        Faker faker=new Faker(new Locale("en-IND"));
        String fName=faker.name().firstName();
        String lName=faker.name().lastName();
        String PNumber=faker.numerify("959#######");
        String AltPhoneNumber=faker.numerify("759#######");
        String Email=faker.internet().emailAddress();
        String AltEmail=faker.internet().emailAddress();
        Customer customer=new Customer(fName,lName,PNumber,AltPhoneNumber,Email,AltEmail);

        String FlatNo=faker.numerify("###");
        String apartment=faker.address().streetName();
        String streetName=faker.address().streetName();
        String landMark=faker.address().streetName();
        String areaName=faker.address().streetName();
        String pincode=faker.numerify("######");

        String state=faker.address().state();

        Customer_Address customerAddress=new Customer_Address(FlatNo,apartment,streetName,areaName,landMark,pincode,COUNTRY,state);
        System.out.println(customerAddress);
        String dop=DateTimeUtil.getDateTimeWithAgo(10);
        String imei=faker.numerify("###############");
        String popurl=faker.internet().url();
        Customer_Product customerProduct=new Customer_Product(dop,imei,imei,imei,popurl,1,1);

        int problmId= faker.random().nextInt(27)+1;
        Problems prob=new Problems(problmId,faker.lorem().sentence(5));
        List<Problems> problemList=new ArrayList<>();
        problemList.add(prob);
        CreateJobPayload payload=new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemList);
        System.out.println(payload);
}
}

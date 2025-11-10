package com.api.utils;

import com.github.javafaker.Faker;

import java.util.Locale;


public class FakerDemo {
    public static void main(String[] args) {
        Locale local=new Locale("en-IND");
        Faker faker=new Faker(new Locale("en-IND"));
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        System.out.println(firstName);
        System.out.println(lastName);
        String buildingNo=faker.address().buildingNumber();

        System.out.println(buildingNo);
        System.out.println(faker.address().streetAddress());
        System.out.println(faker.address().streetName());
        System.out.println(faker.address().city());
        //generating number with some digits constant
        System.out.println(faker.numerify("959#######"));
        //generating number
        System.out.println(faker.number().digit());
        //generating number by giving length it means u will pass how much length the number should be.
        System.out.println(faker.number().digits(13));
        //generating the fake email address
        System.out.println(faker.internet().emailAddress());
        //generating the fake phone number But we prefer to use numerify to generate phone number.
        System.out.println(faker.phoneNumber().cellPhone());


    }
}

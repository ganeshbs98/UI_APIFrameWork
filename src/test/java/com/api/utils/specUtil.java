package com.api.utils;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class specUtil {
    //GET,DELETE
    public static RequestSpecification RequestSpec() {
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(ConfigManager.loadProperties().getProperty("URI")).setContentType(ContentType.JSON).setAccept(ContentType.JSON).log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY).build();
        return requestSpecification;
    }

    //POST,PUT,PATCH
    public static RequestSpecification RequestSpec_withPayload(Object userCredentials) {
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(ConfigManager.loadProperties().getProperty("URI")).setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(userCredentials).log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY).build();
        return requestSpecification;

    }

    public static RequestSpecification RequestSpecAuth(Role role){
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(ConfigManager.loadProperties().getProperty("URI")).setContentType(ContentType.JSON).addHeader("Authorization",AuthTokenProvider.getAuthToken(role)).setAccept(ContentType.JSON).log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY).build();
        return requestSpecification;

    }

    public static ResponseSpecification ResponseSpec_Ok(){
        ResponseSpecification responseSpecification=new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).expectResponseTime(Matchers.lessThan(1000l)).log(LogDetail.ALL).build();
        return  responseSpecification;
    }


    public static ResponseSpecification ResponseSpec_Json(int statusCode){
        ResponseSpecification responseSpecification=new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(statusCode).expectResponseTime(Matchers.lessThan(1000l)).log(LogDetail.ALL).build();
        return  responseSpecification;
    }
    public static ResponseSpecification ResponseSpec_Text(int statusCode){
        ResponseSpecification responseSpecification=new ResponseSpecBuilder().expectStatusCode(statusCode).expectResponseTime(Matchers.lessThan(1000l)).log(LogDetail.ALL).build();
        return  responseSpecification;
    }
}

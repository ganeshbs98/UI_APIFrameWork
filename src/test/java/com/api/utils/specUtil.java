package com.api.utils;

import com.api.constants.Role;
import com.api.filters.SensitiveDataFilter;
import com.api.pojo.UserCredentials;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;

import java.util.Set;

public class specUtil {

    private static final Logger logger = LogManager.getLogger(specUtil.class);

    private specUtil() {

    }

    //GET,DELETE
    @Step("Setting up the Base URI, and content-Type as application/json and attaching the Sensitive data filter")
    public static RequestSpecification RequestSpec() {
        logger.info("Building the request specification with base URI and content type JSON");
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(ConfigManager.loadProperties().getProperty("URI")).setContentType(ContentType.JSON).setAccept(ContentType.JSON).addFilter(new SensitiveDataFilter()).addFilter(new AllureRestAssured()).build();
        return requestSpecification;
    }
    @Step("Building the request specification with query params")
    public static RequestSpecification ResquestSpecWithQueryParams(String paramKey, String paramValue) {
        logger.info("Building the request specification with base URI , content type JSON and query params");
        return new RequestSpecBuilder().setBaseUri(ConfigManager.loadProperties().getProperty("URI")).setContentType(ContentType.JSON).setAccept(ContentType.JSON).addQueryParam(paramKey, paramValue).build();
    }

    //POST,PUT,PATCH
    @Step("Setting up the Base URI, and content-Type as application/json and attaching the Sensitive data filter with payload")
    public static RequestSpecification RequestSpec_withPayload(Object userCredentials) {
        logger.info("Creating request with payload");
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(ConfigManager.loadProperties().getProperty("URI")).addFilter(new SensitiveDataFilter()).setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(userCredentials).addFilter(new AllureRestAssured()).build();
        return requestSpecification;

    }
    @Step("Setting up the Base URI, and content-Type as application/json and attaching the Sensitive data filter for role and attaching payload")
    public static RequestSpecification RequestSpec_withHeader_Payload(Role role, Object userCredentials) {
        logger.info("Creating request with header and payload");
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(ConfigManager.loadProperties().getProperty("URI")).addHeader("Authorization", AuthTokenProvider.getAuthToken(role)).addFilter(new SensitiveDataFilter()).setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(userCredentials).addFilter(new AllureRestAssured()).build();
        return requestSpecification;

    }
    @Step("Setting up the Base URI, and content-Type as application/json and attaching the Sensitive data filter for role")
    public static RequestSpecification RequestSpecAuth(Role role) {
        logger.info("Creating request with auth header");
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(ConfigManager.loadProperties().getProperty("URI")).setContentType(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getAuthToken(role)).setAccept(ContentType.JSON).addFilter(new AllureRestAssured()).build();
        return requestSpecification;

    }
    @Step("Verifying the Response Status code and Response Time")
    public static ResponseSpecification ResponseSpec_Ok() {
        logger.info("Verifying the Response status Code and response Time");
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).expectResponseTime(Matchers.lessThan(1000L)).build();
        return responseSpecification;
    }

    @Step("Verifying the response Spec with status Code: {statusCode}")

    public static ResponseSpecification ResponseSpec_Json(int statusCode) {
        logger.info("Verifying the response Spec with status Code");
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(statusCode).expectResponseTime(Matchers.lessThan(1000L)).build();
        return responseSpecification;
    }

    @Step("Verifying the response Text with status Code: {statusCode}")
    public static ResponseSpecification ResponseSpec_Text(int statusCode) {
        logger.info("Verifying the response Text with status Code");
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(statusCode).expectResponseTime(Matchers.lessThan(1000L)).build();
        return responseSpecification;
    }
}

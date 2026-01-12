package com.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SensitiveDataFilter implements Filter {
    private static final Logger logger= LogManager.getLogger(SensitiveDataFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        logger.info("****** Request Details *******");
        logger.info("Base URI: "+requestSpec.getURI());
        logger.info("Http Method: "+requestSpec.getMethod());
        RedactHeader(requestSpec);
        redactPayload(requestSpec);
        Response response = filterContext.next(requestSpec, responseSpec);
        logger.info("****** Response Details *******");
        logger.info("Status code: \n"+response.getStatusCode());
        logger.info("Response Time: "+response.timeIn(TimeUnit.MILLISECONDS));
        logger.info("Headers: "+response.getHeaders());
        redactResponseBody(response);
        return response;
    }

    private void redactResponseBody(Response response) {
        String respons = response.asPrettyString();
        respons = respons.replaceAll("\"token\"\\s*:\\s*\"[^\"]+\"", "\"token\":\"<redacted>\"");
        System.out.println(respons);
        logger.info("Response Payload :" + respons);
    }



    public void redactPayload(FilterableRequestSpecification requestSpec) {
        if(requestSpec.getBody()!=null){
        String requestPayload = requestSpec.getBody();
        requestPayload = requestPayload.replaceAll("\"password\"\\s*:\\s*\"[^\"]+\"", "\"password\":\"<redacted>\"");
        logger.info("Request Payload :" + requestPayload);
        }
    }
    public void RedactHeader(FilterableRequestSpecification requestSpecification){
      List<Header> headerList = requestSpecification.getHeaders().asList();

      for(Header header : headerList){
          if(header.getName().equalsIgnoreCase("Authorization")){
              logger.info("Header: ", header.getName(), " <redacted>");
          }else{
              logger.info("Header: ",header.getName()+":" + header.getValue());
          }
      }
    }
}

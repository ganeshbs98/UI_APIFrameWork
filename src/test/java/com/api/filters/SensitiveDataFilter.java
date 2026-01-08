package com.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SensitiveDataFilter implements Filter {
    private static final Logger logger= LogManager.getLogger(SensitiveDataFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        System.out.println("---------Hello from Request Filters---------");
        redactPayload(requestSpec);
        Response response = filterContext.next(requestSpec, responseSpec);
        System.out.println("---------Hello from response Filters---------");
        redactResponseBody(response);
        return response;
    }

    private void redactResponseBody(Response response) {
        String respons = response.asPrettyString();
        respons = respons.replaceAll("\"token\"\\s*:\\s*\"[^\"]+\"", "\"token\":\"<redacted>\"");
        System.out.println(respons);
        logger.info("Response Payload :" + respons);
    }

    private String stringifyBody(Object body) {
        if (body == null) return "";
        if (body instanceof String) return (String) body;
        if (body instanceof char[]) return new String((char[]) body);
        if (body instanceof byte[]) return new String((byte[]) body);
        try {
            // Fallback to JSON serialization for POJOs/maps
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            return mapper.writeValueAsString(body);
        } catch (Exception e) {
            // Last resort: use toString()
            return String.valueOf(body);
        }
    }

    public void redactPayload(FilterableRequestSpecification requestSpec) {
        String requestPayload = stringifyBody(requestSpec.getBody());
        requestPayload = requestPayload.replaceAll("\"password\"\\s*:\\s*\"[^\"]+\"", "\"password\":\"<redacted>\"");
        logger.info("Request Payload :" + requestPayload);
    }
}

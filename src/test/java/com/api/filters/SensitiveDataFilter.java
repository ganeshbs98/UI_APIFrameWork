package com.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        System.out.println("---------Hello from Request Filters---------");
        Response response=filterContext.next(filterableRequestSpecification,filterableResponseSpecification);//make the request to the server
        System.out.println("---------Hello from response Filters---------");

        return response;
    }
}

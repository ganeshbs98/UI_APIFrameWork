package Demo.RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class demo1 {
    public static void main(String[] args) {
        RestAssured.baseURI="https://www.google.com";
        RequestSpecification request=RestAssured.given();
        RequestSpecification request2=request.header("Accept","*/*");
        Response response=request2.get();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.asPrettyString());
    }
}

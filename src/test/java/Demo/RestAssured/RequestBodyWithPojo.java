package Demo.RestAssured;

import Demo.RequestPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;

public class RequestBodyWithPojo {
    public static void main(String[] args) {
        RequestPojo requestbody=new RequestPojo("iamfd","password");
        Response response = given()
                .baseUri("http://64.227.160.186:9000/v1")
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestbody).log().all()
                .when()
                .post("login")
                .then().log().all()
                .statusCode(200)
                .and()
                .extract().response();
        System.out.println("response---------"+response.getBody().asPrettyString());
    }
}

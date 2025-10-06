package Demo.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;

import static io.restassured.RestAssured.*;

public class RequestBodyWithMap {
    public static void main(String[] args) {
        LinkedHashMap<String,Object> body=new LinkedHashMap<>();
        body.put("username","iamfd");
        body.put("password","password");
        Response response = given()
                .baseUri("http://64.227.160.186:9000/v1")
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(body).log().all()
                .when()
                .post("login")
                .then().log().all()
                .statusCode(200)
                .and()
                .extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.asPrettyString());
        System.out.println(response.getBody());
        System.out.println(response.time());
    }
}

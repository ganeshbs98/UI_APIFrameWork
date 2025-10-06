package Demo.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.io.File;

import static io.restassured.RestAssured.given;

public class LogiRequestOptimised7 {
    public static void main(String[] args) {
        File f=new File("src/test/resources/userData.json");
        Response response = given()
                .baseUri("http://64.227.160.186:9000/v1")
                .and()
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .and()
                .body(f).log().uri()//logs only the uri of the request
                .log().method()//logs the https method used in the request
                .log().body()//logs the body/payload that is sent during the request
                .log().headers()//logs the headers that are used during the request
                .when()
                .post("login")
                .then().log().ifValidationFails()//logs the response code
                .log().body()
                .and()
                .body("message",equalTo("Success"))//logs the response body
                .and()
                .body("data.token",notNullValue())
                .statusCode(200)
                .and()
                .time(lessThan(1500L))
                .extract().response();
//        System.out.println(response.getStatusCode());
//        System.out.println(response.asPrettyString());
//        System.out.println(response.getBody());
//        System.out.println(response.time());
    }
}

package Demo.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;

public class RequestBodyWithFile {
    public static void main(String[] args) {
        File f=new File("src/test/resources/userData.json");
        Response response = given()
                .baseUri("http://64.227.160.186:9000/v1")
                .and()
                .contentType(ContentType.JSON)
                .and()
                .contentType(ContentType.ANY)
                .and()
                .body(f).log().all()
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

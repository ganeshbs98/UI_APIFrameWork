package Demo.RestAssured;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginApiRequest {
    public static void main(String[] args) {
//        baseURI="http://64.227.160.186:9000/v1";
        String jsonBody = "{\n" +
                "    \"username\": \"iamfd\",\n" +
                "    \"password\": \"password\"\n" +
                "}";
        Response response = given()
                                .baseUri("http://64.227.160.186:9000/v1")
                                .and()
                                .contentType(ContentType.JSON)
                                .and()
                                .body(jsonBody)
                            .when()
                                .post("login")
                            .then()
                                .statusCode(200)
                                .and()
                                .extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.asPrettyString());
        System.out.println(response.getBody());
        System.out.println(response.time());
    }
}
